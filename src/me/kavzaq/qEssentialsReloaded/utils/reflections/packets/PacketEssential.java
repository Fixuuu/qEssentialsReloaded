package me.kavzaq.qEssentialsReloaded.utils.reflections.packets;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.utils.reflections.ReflectionUtils;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import net.minecraft.server.v1_9_R1.Packet;
import net.minecraft.server.v1_9_R1.PlayerConnection;

public class PacketEssential {
	
	public static Class<?> PACKET_PLAY_OUT_PLAYER_INFO = ReflectionUtils.getCraftClass("PacketPlayOutPlayerInfo");
	public static Class<?> PACKET_PLAY_OUT_PLAYER_LIST_HEADER_FOOTER = ReflectionUtils.getCraftClass("PacketPlayOutPlayerListHeaderFooter");
	public static Class<?> PACKET_PLAY_OUT_PLAYER_INFO$ENUM_PLAYER_INFO_ACTION = ReflectionUtils.getCraftClass("PacketPlayOutPlayerInfo$EnumPlayerInfoAction");
	public static Class<?> PACKET_PLAY_OUT_PLAYER_INFO$PLAYER_INFO_DATA = ReflectionUtils.getCraftClass("PacketPlayOutPlayerInfo$PlayerInfoData");
	public static Class<?> ICHAT_BASE_COMPONENT = ReflectionUtils.getCraftClass("IChatBaseComponent");
	public static Class<?> ICHAT_BASE_COMPONENT$CHAT_SERIALIZER = ReflectionUtils.getCraftClass("IChatBaseComponent$ChatSerializer");
	public static Class<?> WORLD_SETTINGS$ENUM_GAMEMODE = ReflectionUtils.getCraftClass("WorldSettings$EnumGamemode");
	public static Class<?> PACKET_PLAY_OUT_ENTITY_METADATA = ReflectionUtils.getCraftClass("PacketPlayOutEntityMetadata");
	public static Class<?> PACKET_PLAY_OUT_ENTITY_DESTROY =	ReflectionUtils.getCraftClass("PacketPlayOutEntityDestroy");
	public static Class<?> PACKET_PLAY_OUT_ENTITY_TELEPORT = ReflectionUtils.getCraftClass("PacketPlayOutEntityTeleport");
	public static Class<?> PACKET_PLAY_OUT_SPAWN_ENTITY_LIVING = ReflectionUtils.getCraftClass("PacketPlayOutSpawnEntityLiving");
	public static Class<?> DATA_WATCHER = ReflectionUtils.getCraftClass("DataWatcher");
	public static Class<?> ENTITY = ReflectionUtils.getCraftClass("Entity");
	public static Class<?> ENTITY_LIVING = ReflectionUtils.getCraftClass("EntityLiving");	
	public static Class<?> ENTITY_ENDER_DRAGON = ReflectionUtils.getCraftClass("EntityEnderDragon");
	
	public static EntityPlayer getPlayerEntity(Player player) {
		return ((CraftPlayer)player).getHandle();
	}
	
	public static PlayerConnection getPlayerConnection(Player player) {
		return getPlayerEntity(player).playerConnection;
	}
	
	public static void sendPacketRadius(Location loc, int radius, Object packet) {
		for (Player p : loc.getWorld().getPlayers()) {
			if (loc.distanceSquared(p.getLocation()) < (radius * radius)) {
				sendPacket(p, packet);
			}
		}
	}
 
	public static void sendPacket(List<Player> players, Object packet) {
		for (Player p : players) {
			sendPacket(p, packet);
		}
	}
 
	public static void sendPacket(Player p, Object packet) {
		try {
			getPlayerConnection(p).sendPacket((Packet<?>) packet);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
