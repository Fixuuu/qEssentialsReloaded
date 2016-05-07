package me.kavzaq.qEssentialsReloaded.impl;

import java.util.Arrays;
import java.util.List;

public class MessagesImpl {
	
	// General
	public static String LANGUAGE = "pl";
	public static String TRANSLATION_AUTHOR = "AdamGrzegorz aka KaVzaQ/KHaliT";
	public static String NO_PERMISSION = "&4Blad: &cNie masz uprawnien do tej komendy! &7(%permission%)";
	public static String ONLY_PLAYER = "&rNie mozesz wykonac tej komendy jako konsola!";
	public static String BAD_ARGS = "&4Blad: &cPoprawne uzycie: ";
	public static String OFFLINE_PLAYER = "&4Blad: &cWyglada na to, ze wybrany gracz jest offline!";
	public static String OFFLINE_PLAYERS = "&4Blad: &cWyglada na to, ze ktorys z wybranych graczy jest offline!";
	public static String BOOLEAN_ON = "wlaczony";
	public static String BOOLEAN_OFF = "wylaczony";
	public static String BOOLEAN_YES = "tak";
	public static String BOOLEAN_NO = "nie";
	public static String SAME_PERSON = "&4Blad: &cNie mozesz wykonac tej operacji na sobie!";
	public static String SAME_PERSONS = "&4Blad: &cNie mozna wykonac tej operacji na tych samych osobach!";
	public static String MUST_BE_INT = "&4Blad: &cJednostka czasu musi byc liczba!";
	
	// Listeners
	public static String CHAT_IS_DISABLED = "&4Blad: &cCzat jest aktualnie wylaczony!";
	public static String CHAT_SLOWDOWN = "&4Blad: &cMusisz poczekac jeszcze &7%remain%&caby cos napisac!";
	
	// Help command
	public static String HELP_HEADER = "&7#--------------- &cPomoc &7[&c%page% &7/&c %pages%&7] ---------------#";
	public static String HELP_INDEX = "&c/%command% &8- &7%description%";
	public static String HELP_UNKNOWN_PAGE = "&4Blad: &cNie znaleziono takiej strony!";
	
	// GameMode command
	public static String GAMEMODE_UNKNOWN = "&4Blad: &cNie znaleziono takiego trybu!";
	public static String GAMEMODE_SUCCESS = "&7Pomyslnie zmieniono tryb gry na: &c%mode%&7!";
	public static String GAMEMODE_OTHER_CHANGED = "&7Gracz &c%player%&7 wlasnie zmienil twoj tryb gry na &c%mode%&7!";
	public static String GAMEMODE_OTHER_SUCCESS = "&7Pomyslnie zmieniono tryb gry na &c%mode%&7 dla gracza &c%player%&7!";
	
	// Broadcast command
	public static String BROADCAST_STYLE = "&8[&cqEssentials&8] &7%message% &8[&cReloaded&8]";
	
	// Heal command
	public static String HEAL_SUCCESS = "&7Uleczono!";
	public static String HEAL_OTHER_HEALED = "&7Zostales uleczony przez &c%player%&7!";
	public static String HEAL_OTHER_SUCCESS = "&7Uleczono gracza &c%player%&7!";

	// Clear command
	public static String CLEAR_SUCCESS = "&7Wyczyszczono!";
	public static String CLEAR_OTHER_CLEARED = "&7Twoj ekwipunek zostal wyczyszczony przez &c%player%&7!";
	public static String CLEAR_OTHER_SUCCESS = "&7Wyczyszczono ekwipunek graczowi &c%player%&7!";
	
	// Item command
	public static String ITEM_SUCCESS = "&7Otrzymales: &c%amount% x %item%";
	public static String ITEM_UNKNOWN = "&4Blad: &cSyntax twojego przedmiotu jest nieprawidlowy! Sprawdz czy syntax jest zgodny, lub popraw bledy!";
	public static String ITEM_OTHER_GIVE = "&7Otrzymales: &c%amount% x %item% &7od &c%player%&7!";
	public static String ITEM_OTHER_SUCCESS = "&7Pomyslnie dales graczowi &c%player%&7 przedmiot &c%item% &7w ilosci &c%amount%&7!";
	
	// Teleporting
	public static String TELEPORT_PROCESS = "&7Teleport nastapi za &c%delay% &7sekund...";
	public static String TELEPORT_SUCCESS = "&7Pomyslnie przeteleportowano!";
	public static String TELEPORT_DENY = "&cZe wzgledu na ruch, teleport jest anulowany.";
	public static String TELEPORT_SAME_PERSON = "&4Blad: &cNie mozesz wykonac operacji teleportu na sobie!";
	public static String TPA_REQUEST_SENDED = "&7Pomyslnie wyslano prosbe o teleportacje do gracza &c%player%&7!";
	public static List<String> TPA_REQUEST_GOT = Arrays.asList(
			"&7Gracz &c%player% &7chce sie do ciebie przeteleportowac!",
			"&7Wpisz &c/tpaccept &7aby zaakceptowac teleport.",
			"&7Wpisz &c/tpdeny &7aby odrzucic teleport.",
			"&7Pozostalo ci &c60 sekund &7aby potwierdzic teleport!");
	public static String TPACCEPT_ACCEPTED = "&7Pomyslnie akceptowales prosbe o teleportacje gracza &c%player%&7!";
	public static String TPACCEPT_OTHER_ACCEPTED = "&7Twoja prosba o teleportacja zostala przyjeta przez gracza &c%player%&7!";
	public static String TELEPORT_NO_REQUEST = "&4Blad: &cNie masz aktualnie oczekujacej prosby o teleportacje!";
	public static String TPDENY_DENIED = "&7Pomyslnie odmowiles na prosbe o teleportacje gracza &c%player%&7!";
	public static String TPDENY_OTHER_DENIED = "&7Twoja prosba o teleportacje zostala odrzucona przez gracza &c%player%&7!";
	public static String TPA_ALREADY_REQUESTED = "&4Blad: &cJuz wyslales zaproszenie do tej osoby!";
	public static String TPA_HAS_REQUEST_PENDING = "&4Blad: &cWyglada na to, ze osoba do ktorej chcesz wyslac zaproszenie, juz jedno ma w kolejce! Odczekaj chwile!";
	public static String TP_ALL_SUCCESS = "&7Przeteleportowano do ciebie wszystkich na serwerze";
	public static String TP_FROM_TO_SUCCESS = "&7Przeteleportowano gracza &c%player_from%&7 do gracza &c%player_to%";
	public static String TP_TELEPORTED = "&7Zostales przeteleportowanny do gracza &c%player%&7!";
	
	// Back command
	public static String BACK_SUCCESS = "&7Zostales przeteleportowany do twojego ostatniego polozenia.";
	public static String BACK_OTHER = "&7Zostales przeteleportowany do ostatniego polozenia gracza &c%player%&7!";
	
	// Feed command
	public static String FEED_SUCCESS = "&7Najedzono!";
	public static String FEED_OTHER_FEEDED = "&7Zostales najedzony przez &c%player%&7!";
	public static String FEED_OTHER_SUCCESS = "&7Najedzono gracza &c%player%&7!";
	
	// Fly command
	public static String FLY_SWITCHED = "&7Pomyslnie ustawiono tryb latania na &c%mode%";
	public static String FLY_OTHER_SWITCHED = "&7Gracz &c%player%&7 zmienil ci tryb latania na &c%mode%";
	public static String FLY_OTHER_SUCCESS = "&7Pomyslnie zmieniles graczowi &c%player%&7 tryb latania na &c%mode%";
	
	// God command
	public static String GOD_SWITCHED = "&7Pomyslnie ustawiono tryb &cniesmiertelnosci&7 na &c%mode%";
	public static String GOD_OTHER_ACTIVATED = "&7Gracz &c%player%&7 zmienil ci tryb &cniesmiertelnosci&7 na &c%mode%";
	public static String GOD_OTHER_SUCCESS = "&7Pomyslnie zmieniles graczowi &c%player%&7 tryb &cniesmiertelnosci&7 na &c%mode%";
	
	
	// Setspawn command
	public static String SETSPAWN_SUCCESS = "&7Pomyslnie ustawiono spawn! &7Swiat: &c%world%, &7Koordynaty: &c%coords%";

	// Spawn command
	public static String SPAWN_OTHER_SUCCESS = "&7Przeteleportowano gracza &c%player%&7 na spawn!";
	public static String SPAWN_OTHER_TELEPORTED = "&7Zostales przeteleportowany na spawn przez gracza &c%player%&7!";
	
	// Chat command
	public static String CHAT_DISABLED = "&cCzat zostal wylaczony przez &7%player%&c!";
	public static String CHAT_ENABLED = "&aCzat zostal wlaczony przez &7%player%&a!";
	public static String CHAT_CLEARED = "&eCzat zostal wyczyszczony przez &7%player%&e!";
	
	// Changename command
	public static String CHANGENAME_BAD_ITEM = "&4Blad: &cNie mozesz zmienic nazwy powietrza!";
	public static String CHANGENAME_SUCCESS = "&7Pomyslnie zmieniles nazwe przedmiotu na &c%name%&7!";
	
	// Weather command
	public static String WEATHER_SUNNY_SUCCESS = "&7Pomyslnie ustawiono pogode na &csloneczna&7! (&c%world%&7)";
	public static String WEATHER_THUNDER_SUCCESS = "&7Pomyslnie ustawiono pogode na &cdeszczowa&7! (&c%world%&7)";
	
	// Time command
	public static String TIME_DAY_SUCCESS = "&7Pomyslnie ustawiono &cdzien [2500]&7! (&c%world%&7)";
	public static String TIME_NIGHT_SUCCESS = "&7Pomyslnie ustawiono &cnoc [14500]&7! (&c%world%&7)";
	public static String TIME_OWN_SUCCESS = "&7Pomyslnie ustawiono czas na &c%time%&7! (&c%world%&7)";
	
	// List command
	public static String LIST_HEADER = "&7Na serwerze jest &c%players%&7 na maksimum &c%maxplayers%&7 graczy online!";
	public static String LIST_INDEX = "&7Gracze: &c%players%";
	
	// World command
	public static List<String> WORLD_LIST_HEADER = Arrays.asList(
			"&7Wpisz &c/world <world>&7 aby wybrac swiat.",
			"&7Wszystkie dostepne swiaty:");
	public static String WORLD_INDEX = "&7  * &c%world%";
	public static String WORLD_NOT_EXISTS = "&4Blad: &cWybrany swiat nie istnieje!";
	
	// WhoIs command
	public static List<String> WHOIS_INFO = Arrays.asList(
			"&7Informacje na temat gracza &c%player%&7:",
			"&7  UUID: &c%uuid%",
			"&7  Adres IP: &c%addressIp%",
			"&7  Globalne upraw.: &c%isGlobalAdmin%",
			"&7  Tryb gry: &c%mode%",
			"&7  Lata: &c%flying%", 
			"&7  Zywnosc: &c%food%/10",
			"&7  Zycie: &c%health%/10",
			"&7  Lokacja: &c%location%", 
			"&7  Niesmiertelnosc: &c%isGod%", 
			"&7  Domy:",
			"%homes%");
	
	public static String WHOIS_HOMES_INDEX = "&7  * &c%home% &7: &c%location%";
	
	// SetHome command
	public static String SETHOME_MAX = "&4Blad: &cOsiagnieto maksymalna ilosc domow!";
	public static String SETHOME_HAS_THIS_NAME = "&4Blad: &cJuz masz dom o tej nazwie!";
	public static String SETHOME_SUCCESS = "&7Pomyslnie dodales nowy dom o nazwie &c%home%&7!";
	
	// Home command
	public static String HOME_UNKNOWN = "&4Blad: &cNie znaleziono domu o takiej nazwie!";
	public static String HOME_LIST_HEADER = "&7Lista twoich domow: ";
	public static String HOME_SUCCESS = "&7Przeteleportowano do domu o nazwie &c%home%&7!";
	
	// Delhome command
	public static String DELHOME_UNKNOWN = "&4Blad: &cNie znaleziono domu o takiej nazwie!";
	public static String DELHOME_SUCCESS = "&7Pomyslnie usunales dom o nazwie &c%home%&7!";
	
	// Message command
	public static String MESSAGE_TO_FORMAT = "&7[&cJa&7 -> &c%player%&7] &c%message%";
	public static String MESSAGE_FROM_FORMAT = "&7[&c%player% &7-> &cJa&7] &c%message%";
	
	// Reply command
	public static String REPLY_OFFLINE_OR_NO_CONV = "&4Blad: &cGracz jest offline lub nie masz ostatniej konwersacji!";
	
	// Kit command
	public static String KIT_SUCCESS = "&7Otrzymales zestaw o nazwie &c%name%&7!";
	public static String KIT_COOLDOWN = "&4Blad: &cMusisz zaczekac jeszcze &7%cooldown%&c zanim wezmiesz kolejny zestaw!";
	public static String KIT_LIST = "&7Dostepne zestawy: &c";
	public static String KIT_UNKNOWN = "&4Blad: &cTaki zestaw nie istnieje!";
	
	// Enchant command
	public static String ENCHANT_UNKNOWN = "&4Blad: &cNie znaleziono takiego zaklecia!";
	public static String ENCHANT_SUCCESS = "&7Pomyslnie zaklnales swoj przedmiot na &c%enchantment%:%power%&7!";
	public static String ENCHANT_NULL_ITEM = "&4Blad: &cNie trzymasz zadnego przedmiotu w glownej rece!";
	
	// Helpop command
	public static String HELPOP_COOLDOWN = "&4Blad: &cMusisz zaczekac jeszcze &7%cooldown%&c zanim napiszesz kolejna wiadomosc!";
	public static String HELPOP_FORMAT = "&8[&4HELPOP&8] &8<&c%player%&8> &c%message%";
	
	// Head command
	public static String HEAD_SUCCESS = "&7Pomyslnie otrzymales glowe gracza &c%player%&7!";
	public static String HEAD_NAME = "&7Glowa gracza: &c%player%";
	
	// Gc command
	public static List<String> GARBAGECOLLECTOR_INFO = Arrays.asList(
			"&7Informacje na temat serwera:",
			"  &7Wydajnosc: ",
			"     &7Aktualny TPS: &c%tps% &7(&c%percentage% overload&7)",
			"     &7Srednie TPS: ",
			"        &7ostatnia 1 minuta: &c%1mAvgTPS% &7(&c%1mAvgPercentage% overload&7)",
			"        &7ostatnie 5 minut: &c%5mAvgTPS% &7(&c%5mAvgPercentage% overload&7)", 
			"        &7ostatnie 15 minut: &c%15mAvgTPS% &7(&c%15mAvgPercentage% overload&7)",
			"  &7Uptime: &c%uptime%",
			"  &7Dostepne rdzenie: &c%cores%",
			"  &7System: &c%os%",
			"  &7Java: &c%java%",
			"  &7Maksymalna pamiec: &c%maxMemory%",
			"  &7Alokowana pamiec: &c%totalMemory%", 
			"  &7Wolna pamiec: &c%freeMemory%", 
			"  &7Swiaty:", 
			"%worlds%");
	public static String GARBAGECOLLECTOR_WORLD_FORMAT = "&c    %world%&7 -> &c%chunks% &7chunkow, &c%objects% &7obiektow, &c%tiles% &7tilesow.";
	
	// Repair command
	public static String REPAIR_UNKNOWN = "&4Blad: &cPrzedmiotu ktory trzymasz w glownej rece nie da sie naprawic!";
	public static String REPAIR_SUCCESS = "&7Pomyslnie naprawiles przedmiot w twojej glownej rece!";
	public static String REPAIR_ALL_SUCCESS = "&7Pomyslnie naprawiles caly ekwipunek!";
	public static String REPAIR_ARMOR_SUCCESS = "&7Pomyslnie naprawiles swoja zbroje!";
	
	// Commands desc
	public static String BACK_DESC = "Teleportacja do ostatniej lokacji";
	public static String BROADCAST_DESC = "Globalne ogloszenie";
	public static String CHAT_DESC = "Zarzadzanie czatem";
	public static String CLEARINV_DESC = "Czyszczenie ekwipunku";
	public static String DELHOME_DESC = "Usuniecie istniejacego domu";
	public static String FEED_DESC = "Nakarmienie siebie lub kogos innego";
	public static String FLY_DESC = "Przelacza tryb latania";
	public static String GAMEMODE_DESC = "Zarzadzanie trybem gry";
	public static String GOD_DESC = "Przelacza tryb niesmiertelnosci";
	public static String HEAL_DESC = "Uleczenie siebie lub kogos innego";
	public static String HELP_DESC = "Zbior dostepnych komend";
	public static String HOME_DESC = "Teleportacja do istniejacego domu";
	public static String ITEM_DESC = "Pobieranie dowolnej ilosci przedmiotu";
	public static String LIST_DESC = "Wyswietla liste graczy online";
	public static String MESSAGE_DESC = "Prywatne wiadomosci miedzy graczami";
	public static String REPLY_DESC = "Odpowiada na wiadomosc gracza";
	public static String SETHOME_DESC = "Dodaje nowy dom";
	public static String SETSPAWN_DESC = "Ustawia pozycje spawnu";
	public static String SPAWN_DESC = "Teleportuje na spawn";
	public static String TIME_DESC = "Manipulacja czasem";
	public static String TP_ACC_DESC = "Akceptuje prosbe o teleportacje";
	public static String TPA_DESC = "Wysyla zaproszenie do teleportacji";
	public static String TP_DESC = "Zarzadzanie teleportem";
	public static String TP_DENY_DESC = "Odrzuca prosbe o teleportacje";
	public static String WEATHER_DESC = "Manipulacja pogoda";
	public static String WHOIS_DESC = "Informacje na temat gracza";
	public static String WORLD_DESC = "Teleportacja miedzy swiatami";
	public static String KIT_DESC = "Zarzadzanie zestawami";
	public static String GIVE_DESC = "Daje przedmiot pewnej osobie";
	public static String ENCHANT_DESC = "Zaklina przedmiot w twojej glownej rece";
	public static String INVSEE_DESC = "Otwiera ekwipunek wybranej osoby";
	public static String HELPOP_DESC = "Wiadomosc do administratorow";
	public static String HEAD_DESC = "Daje glowe gracza";
	public static String ENDERCHEST_DESC = "Zarzadzanie enderchestem";
	public static String GARBAGECOLLECTOR_DESC = "Wyswietla informacje na temat serwera";
	public static String REPAIR_DESC = "Zarzadzanie naprawiem ekwipunku";
}
