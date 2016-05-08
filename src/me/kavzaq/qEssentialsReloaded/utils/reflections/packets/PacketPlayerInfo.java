package me.kavzaq.qEssentialsReloaded.utils.reflections.packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.mojang.authlib.GameProfile;

import me.kavzaq.qEssentialsReloaded.impl.TabConfigurationImpl;
import me.kavzaq.qEssentialsReloaded.interfaces.User;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.utils.json.JSONPacketBuilder;
import me.kavzaq.qEssentialsReloaded.utils.reflections.ReflectionUtils;

public class PacketPlayerInfo {
	
	private PacketPlayerInfo() { }
	
	private static Object PACKET_PLAY_OUT_PLAYER_INFO_CONSTRUCTOR = 
			ReflectionUtils.getConstructor(PacketEssential.PACKET_PLAY_OUT_PLAYER_INFO);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getPlayerInfo(User user, GameProfile gp, String slot) {
		Constructor<?> cons = null;
		try {
			cons = PacketEssential.PACKET_PLAY_OUT_PLAYER_INFO$PLAYER_INFO_DATA
					.getDeclaredConstructor(PacketEssential.PACKET_PLAY_OUT_PLAYER_INFO, GameProfile.class, int.class, 
							PacketEssential.WORLD_SETTINGS$ENUM_GAMEMODE, PacketEssential.ICHAT_BASE_COMPONENT);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			return cons.newInstance(PACKET_PLAY_OUT_PLAYER_INFO_CONSTRUCTOR, 
					gp, 
					TabConfigurationImpl.tablistPing, 
					Enum.valueOf((Class<Enum>) PacketEssential.WORLD_SETTINGS$ENUM_GAMEMODE, "SURVIVAL"), 
					JSONPacketBuilder.build("{\"text\": \"" + Util.fixColors(slot) + "\"}"));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}
