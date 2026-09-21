package com.aoe.fytcanbusmonitor

object BluetoothUpdateCodes {
    val U_BTAV_ID3_TITLE = 0
    val U_BTAV_ID3_ARTIST = 1
    val U_BTAV_TOTAL_TIME = 2
    val U_BTAV_PLAY_TRACK = 3
    val U_BTAV_TOTAL_TRACK = 4
    val U_PAIR_LIST = 5
    val U_PHONE_MAC_ADDR = 6
    val U_PHONE_NAME = 7
    val U_PHONE_NUMBER = 8
    val U_PHONE_STATE = 9
    val U_HANG_CLEAR_NUM = 10
    val U_RING_TIME = 11
    val U_TALK_TIME = 12
    val U_BTAV_PLAY_STATE = 13
    val U_LOCAL_MAC_ADDR = 14
    val U_LOCAL_NAME = 15
    val U_PIN_CODE = 16
    val U_BT_VER = 17
    val U_HFP = 18
    val U_RESET = 19
    val U_AUTOPICK = 20
    val U_AUTOPICK_REMAIN = 21
    val U_BOOK = 22
    val U_PHONE_TYPE = 23
    val U_SEARCH_LIST = 24
    val U_PAIR_RESULT = 25
    val U_BTAV_ID3_ALBUM = 26
    val U_BTAV_GENRE = 27
    val U_BTAV_PLAY_TIME = 28
    val U_PBAP_STATE = 29
    val U_AVRCP14_SUPPORT = 30
    val U_BT_POWER_ON = 31
    val U_MIC_LEVEL = 32
    val U_BTRING_PERCENT = 33
    val U_A2DP_SINK_STATE = 34
    val U_RECORD = 35
    val U_UPDATE_PROGRESS = 36

    var MUTE_MIC = 42
    var PHONE_VOICE = 43
    var PHONE_BATTERY = 44
    var PHONE_SIGNAL = 45
    var PHONE_OPERATOR = 46
    var BLUETOOTH_CODING = 47
    var ODB_DEV_CONNECTSTATE = 48
    var PHONE_MESSAGE_HANDLE = 49
    var PHONE_MESSAGE = 50

    val U_CNT_MAX = 256
}

object BluetoothCommandCodes {
    val C_BTAV_PREV = 0
    val C_BTAV_NEXT = 1
    val C_BTAV_PLAYPAUSE = 2
    val C_BTAV_PAUSE = 3
    val C_BTAV_STOP = 4
    val C_QUERY_PAIR = 5
    val C_KEY = 6
    val C_DIAL = 7
    val C_REDIAL = 8
    val C_PICKUP = 9
    val C_HANG = 10
    val C_REJECT_RING = 11
    val C_NUMBER = 12
    val C_LINK_CUT = 13
    val C_HFP = 14
    val C_CLEAR = 15
    val C_HANG_CLEAR_NUM = 16
    val C_RESET = 17
    val C_PIN_CODE = 18
    val C_LOCAL_NAME = 19
    val C_DISCOVER = 20
    val C_TEST = 21
    val C_MIC_VOL = 22
    val C_MIC_LEVEL = 23
    val C_CONNECT_DEVICE = 24
    val C_CONNECT_OBD = 25
    val C_DOWNLOAD_BOOK = 26
    val C_BTAV_PLAY = 27  
    val C_AUTOPICK = 28
    val C_BT_POWER_ON = 29
    val C_BTRING_PERCENT = 30
    val C_BTAV_LINK_CUT = 31
    val C_BT_HOLD = 32
    val C_BT_HOLD_HANG = 33
    val C_BT_CURTALK_HANG = 34
    val C_BT_DIAL_RECORD = 35
    val C_BT_RING_RECORD = 36
    val C_BT_HANG_RECORD = 37
    val C_BT_DEVICE_UPDATE_FILE = 38
    val C_CONSOLIDATED_CALLS = 39
    val C_BT_DEL_RECORD_ONE = 40
    val C_BT_DEL_RECORD_ALL = 41    
}

  /**
  * No known Bluetooth module get codes
  **/
object BluetoothGetCodes {}

object BluetoothOptions {
            var BASE = 42
            
            var MUTE_MIC = 42
            var PHONE_VOICE = 43
            var PHONE_BATTERY = 44
            var PHONE_SIGNAL = 45
            var PHONE_OPERATOR = 46
            var BLUETOOTH_CODING = 47
            var ODB_DEV_CONNECTSTATE = 48
            var PHONE_MESSAGE_HANDLE = 49
            var PHONE_MESSAGE = 50
            var BTAV_ABLUME_IMG = 51
            var C_BT_RECORD_5LIST = 52
            var C_BT_NUMBER_NAME = 53
            var CALLING_PIP_ENABLE = 55
            var STOP_DOWNLOAD_BOOK = 56
            var TERMINAL_CMD = 57
            var BLE_STEER_DEV_STATE = 58
            var TRANSFER = 59
            var CALL_NUMBER = 60
            var HFP_STATUS = 61
            var A2DP_STATUS = 62
            var WAITING_NUMBER = 63
            var SUPPORT_MULTI = 64
            var ANSWERING_DEVICE = 65
            var SWAP_DEVICES_INDEX = 66
        }
