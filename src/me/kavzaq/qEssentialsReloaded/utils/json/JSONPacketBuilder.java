package me.kavzaq.qEssentialsReloaded.utils.json;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.kavzaq.qEssentialsReloaded.utils.reflections.ReflectionUtils;
import me.kavzaq.qEssentialsReloaded.utils.reflections.packets.PacketEssential;

public class JSONPacketBuilder {
	
	public static Object build(String content) {
		Method method = ReflectionUtils.getTypedMethod(
				PacketEssential.ICHAT_BASE_COMPONENT$CHAT_SERIALIZER, "a", 
				PacketEssential.ICHAT_BASE_COMPONENT, String.class); 
		
		try {
			return method.invoke(null, content);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}
