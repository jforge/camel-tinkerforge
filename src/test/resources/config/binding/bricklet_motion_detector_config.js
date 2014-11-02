{
	"category" : "Bricklet",
	"description" : "Device that reads out PIR motion detector",
	"author" : "Olaf L\u00fcke <olaf@tinkerforge.com>",
	"packets" : [
			{
				"elements" : [ [
						"motion",
						"uint8",
						1,
						"out",
						[
								"Motion",
								"motion",
								[ [ "NotDetected", "not_detected", 0 ],
										[ "Detected", "detected", 1 ] ] ] ] ],
				"name" : [ "GetMotionDetected", "get_motion_detected" ],
				"doc" : [
						"bf",
						{
							"de" : "\nGibt 1 zur\u00fcck wenn eine Bewegung detektiert wurde. Wie lange 1 zur\u00fcckgegeben\nwird nachdem eine Bewegung detektiert wurde kann mit einem kleinen Poti auf\ndem Motion Detector Bricklet eingestellt werden, siehe :ref:`hier\n<motion_detector_bricklet_sensitivity_delay_block_time>`.\n\nAuf dem Bricklet selbst ist eine blaue LED, die leuchtet solange das Bricklet\nim \"Bewegung detektiert\" Zustand ist.\n",
							"en" : "\nReturns 1 if a motion was detected. How long this returns 1 after a motion\nwas detected can be adjusted with one of the small potentiometers on the\nMotion Detector Bricklet, see :ref:`here\n<motion_detector_bricklet_sensitivity_delay_block_time>`.\n\nThere is also a blue LED on the Bricklet that is on as long as the Bricklet is\nin the \"motion detected\" state.\n"
						} ],
				"since_firmware" : [ 1, 0, 0 ],
				"function_id" : 1,
				"type" : "function"
			},
			{
				"elements" : [],
				"name" : [ "MotionDetected", "motion_detected" ],
				"doc" : [
						"c",
						{
							"de" : "\nDieser Callback wird aufgerufen nachdem eine Bewegung detektiert wurde.\n",
							"en" : "\nThis callback is called after a motion was detected.\n"
						} ],
				"since_firmware" : [ 1, 0, 0 ],
				"function_id" : 2,
				"type" : "callback"
			},
			{
				"elements" : [],
				"name" : [ "DetectionCycleEnded", "detection_cycle_ended" ],
				"doc" : [
						"c",
						{
							"de" : "\nDieser Callback wird aufgerufen wenn ein Bewegungserkennungszyklus\nbeendet ist. Wenn dieser Callback aufgerufen wurde kann wieder\neine weitere Bewegung erkannt werden nach ungef\u00e4hr 2 Sekunden.\n",
							"en" : "\nThis callback is called when the detection cycle ended. When this\ncallback is called, a new motion can be detected again after approximately 2\nseconds.\n"
						} ],
				"since_firmware" : [ 1, 0, 0 ],
				"function_id" : 3,
				"type" : "callback"
			},
			{
				"elements" : [ [ "api_version", "uint8", 3, "out" ] ],
				"name" : [ "GetAPIVersion", "get_api_version" ],
				"doc" : [
						"af",
						{
							"de" : "\nGibt die Version der API Definition (Major, Minor, Revision) zur\u00fcck, die diese\nAPI Bindings implementieren. Dies ist werder die Release-Version dieser API\nBindings noch gibt es in irgendeiner Weise Auskunft \u00fcber den oder das\nrepr\u00e4sentierte(n) Brick oder Bricklet.\n",
							"en" : "\nReturns the version of the API definition (major, minor, revision) implemented\nby this API bindings. This is neither the release version of this API bindings\nnor does it tell you anything about the represented Brick or Bricklet.\n"
						} ],
				"is_virtual" : true,
				"since_firmware" : null,
				"function_id" : -1,
				"type" : "function"
			},
			{
				"elements" : [ [ "function_id", "uint8", 1, "in" ],
						[ "response_expected", "bool", 1, "out" ] ],
				"name" : [ "GetResponseExpected", "get_response_expected" ],
				"doc" : [
						"af",
						{
							"de" : "\nGibt das Response-Expected-Flag f\u00fcr die Funktion mit der angegebenen Funktions\nIDs zur\u00fcck. Es ist *true* falls f\u00fcr die Funktion beim Aufruf eine Antwort\nerwartet wird, *false* andernfalls.\n\nF\u00fcr Getter-Funktionen ist diese Flag immer gesetzt und kann nicht entfernt\nwerden, da diese Funktionen immer eine Antwort senden. F\u00fcr\nKonfigurationsfunktionen f\u00fcr Callbacks ist es standardm\u00e4\u00dfig gesetzt, kann aber\nentfernt werden mittels :func:`SetResponseExpected`. F\u00fcr Setter-Funktionen ist\nes standardm\u00e4\u00dfig nicht gesetzt, kann aber gesetzt werden.\n\nWenn das Response-Expected-Flag f\u00fcr eine Setter-Funktion gesetzt ist, k\u00f6nnen\nTimeouts und andere Fehlerf\u00e4lle auch f\u00fcr Aufrufe dieser Setter-Funktion\ndetektiert werden. Das Ger\u00e4t sendet dann eine Antwort extra f\u00fcr diesen Zweck.\nWenn das Flag f\u00fcr eine Setter-Funktion nicht gesetzt ist, dann wird keine\nAntwort vom Ger\u00e4t gesendet und Fehler werden stillschweigend ignoriert, da sie\nnicht detektiert werden k\u00f6nnen.\n\nSiehe :func:`SetResponseExpected`\nf\u00fcr die Liste der verf\u00fcgbaren Funktions ID :word:`constants` f\u00fcr diese Funktion.\n",
							"en" : "\nReturns the response expected flag for the function specified by the function\nID parameter. It is *true* if the function is expected to send a response,\n*false* otherwise.\n\nFor getter functions this is enabled by default and cannot be disabled,\nbecause those functions will always send a response. For callback configuration\nfunctions it is enabled by default too, but can be disabled by\n:func:`SetResponseExpected`. For setter functions it is disabled by default\nand can be enabled.\n\nEnabling the response expected flag for a setter function allows to detect\ntimeouts and other error conditions calls of this setter as well. The\ndevice will then send a response for this purpose. If this flag is disabled for\na setter function then no response is send and errors are silently ignored,\nbecause they cannot be detected.\n\nSee :func:`SetResponseExpected`\nfor the list of function ID :word:`constants` available for this function.\n"
						} ],
				"is_virtual" : true,
				"since_firmware" : null,
				"function_id" : -1,
				"type" : "function"
			},
			{
				"elements" : [ [ "function_id", "uint8", 1, "in" ],
						[ "response_expected", "bool", 1, "in" ] ],
				"name" : [ "SetResponseExpected", "set_response_expected" ],
				"doc" : [
						"af",
						{
							"de" : "\n\u00c4ndert das Response-Expected-Flag f\u00fcr die Funktion mit der angegebenen Funktion\nIDs. Diese Flag kann nur f\u00fcr Setter-Funktionen (Standardwert: *false*) und\nKonfigurationsfunktionen f\u00fcr Callbacks (Standardwert: *true*) ge\u00e4ndert werden.\nF\u00fcr Getter-Funktionen ist das Flag immer gesetzt und f\u00fcr Callbacks niemals.\n\nWenn das Response-Expected-Flag f\u00fcr eine Setter-Funktion gesetzt ist, k\u00f6nnen\nTimeouts und andere Fehlerf\u00e4lle auch f\u00fcr Aufrufe dieser Setter-Funktion\ndetektiert werden. Das Ger\u00e4t sendet dann eine Antwort extra f\u00fcr diesen Zweck.\nWenn das Flag f\u00fcr eine Setter-Funktion nicht gesetzt ist, dann wird keine\nAntwort vom Ger\u00e4t gesendet und Fehler werden stillschweigend ignoriert, da sie\nnicht detektiert werden k\u00f6nnen.\n",
							"en" : "\nChanges the response expected flag of the function specified by the\nfunction ID parameter. This flag can only be changed for setter (default value:\n*false*) and callback configuration functions (default value: *true*). For\ngetter functions it is always enabled and callbacks it is always disabled.\n\nEnabling the response expected flag for a setter function allows to detect\ntimeouts and other error conditions calls of this setter as well. The\ndevice will then send a response for this purpose. If this flag is disabled for\na setter function then no response is send and errors are silently ignored,\nbecause they cannot be detected.\n"
						} ],
				"is_virtual" : true,
				"since_firmware" : null,
				"function_id" : -1,
				"type" : "function"
			},
			{
				"elements" : [ [ "response_expected", "bool", 1, "in" ] ],
				"name" : [ "SetResponseExpectedAll",
						"set_response_expected_all" ],
				"doc" : [
						"af",
						{
							"de" : "\n\u00c4ndert das Response-Expected-Flag f\u00fcr alle Setter-Funktionen und\nKonfigurationsfunktionen f\u00fcr Callbacks diese Ger\u00e4tes.\n",
							"en" : "\nChanges the response expected flag for all setter and callback configuration\nfunctions of this device at once.\n"
						} ],
				"is_virtual" : true,
				"since_firmware" : null,
				"function_id" : -1,
				"type" : "function"
			},
			{
				"elements" : [ [ "uid", "string", 8, "out" ],
						[ "connected_uid", "string", 8, "out" ],
						[ "position", "char", 1, "out" ],
						[ "hardware_version", "uint8", 3, "out" ],
						[ "firmware_version", "uint8", 3, "out" ],
						[ "device_identifier", "uint16", 1, "out" ] ],
				"name" : [ "GetIdentity", "get_identity" ],
				"doc" : [
						"af",
						{
							"de" : "\nGibt die UID, die UID zu der das Bricklet verbunden ist, die\nPosition, die Hard- und Firmware Version sowie den Device Identifier\nzur\u00fcck.\n\nDie Position kann 'a', 'b', 'c' oder 'd' sein.\n\nEine Liste der Device Identifier Werte ist :ref:`hier <device_identifier>` zu\nfinden. |device_identifier_constant|\n",
							"en" : "\nReturns the UID, the UID where the Bricklet is connected to, \nthe position, the hardware and firmware version as well as the\ndevice identifier.\n\nThe position can be 'a', 'b', 'c' or 'd'.\n\nThe device identifier numbers can be found :ref:`here <device_identifier>`.\n|device_identifier_constant|\n"
						} ],
				"prototype_in_device" : true,
				"since_firmware" : [ 2, 0, 0 ],
				"function_id" : 255,
				"type" : "function"
			} ],
	"device_identifier" : 233,
	"released" : true,
	"common_included" : true,
	"manufacturer" : "Tinkerforge",
	"api_version" : [ 2, 0, 0 ],
	"name" : [ "MotionDetector", "motion_detector", "Motion Detector" ]
}