/*
 *   Copyright (C) 2014 GeorgH93
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package at.pcgamingfreaks.georgh.MarriageMaster.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import at.pcgamingfreaks.georgh.MarriageMaster.MarriageMaster;

public class MarryChat 
{
	private MarriageMaster marriageMaster;
	public List<Player> pcl;
	
	public MarryChat(MarriageMaster marriagemaster)
	{
		marriageMaster = marriagemaster;
		pcl = new ArrayList<Player>();
	}

	public void Chat(Player sender, Player reciver, String msg)
	{
		if(reciver != null && reciver.isOnline())
		{
			msg = msg.replace('�', '&');
			if(marriageMaster.config.CheckPerm(sender, "marry.chat.color"))
			{
				ChatColor.translateAlternateColorCodes('&', msg);
			}
			if(marriageMaster.config.CheckPerm(sender, "marry.chat.format"))
			{
				msg = msg.replaceAll("&l", "�l").replaceAll("&m", "�m").replaceAll("&n", "�n").replaceAll("&o", "�o").replaceAll("&r", "�r");
			}
			else
			{
				msg = msg.replaceAll("�l", "&l").replaceAll("�m", "&m").replaceAll("�n", "&n").replaceAll("�o", "&o").replaceAll("�r", "&r");
			}
			if(marriageMaster.config.CheckPerm(sender, "marry.chat.magic"))
			{
				msg = msg.replaceAll("&k", "�k");
			}
			else
			{
				msg = msg.replaceAll("�k", "&k");
			}
			msg = sender.getDisplayName() + ChatColor.WHITE + " => " + reciver.getDisplayName() + ChatColor.WHITE + ": " + msg;
			reciver.sendMessage(msg);
			sender.sendMessage(msg);
			for (Player play : pcl)
			{
				play.sendMessage(msg);
			}
		}
		else
		{
			sender.sendMessage(ChatColor.RED + marriageMaster.lang.Get("Ingame.PartnerOffline"));
		}
	}
}