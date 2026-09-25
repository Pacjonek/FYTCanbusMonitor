package com.aoe.fytcanbusmonitor

object BluetoothUpdateCodes {
    const val U_BTAV_ID3_TITLE = 0
    const val U_BTAV_ID3_ARTIST = 1
    const val U_BTAV_TOTAL_TIME = 2
    const val U_BTAV_PLAY_TRACK = 3
    const val U_BTAV_TOTAL_TRACK = 4
    const val U_PAIR_LIST = 5
    const val U_PHONE_MAC_ADDR = 6
    const val U_PHONE_NAME = 7
    const val U_PHONE_NUMBER = 8
    const val U_PHONE_STATE = 9
    const val U_HANG_CLEAR_NUM = 10
    const val U_RING_TIME = 11
    const val U_TALK_TIME = 12
    const val U_BTAV_PLAY_STATE = 13
    const val U_LOCAL_MAC_ADDR = 14
    const val U_LOCAL_NAME = 15
    const val U_PIN_CODE = 16
    const val U_BT_VER = 17
    const val U_HFP = 18
    const val U_RESET = 19
    const val U_AUTOPICK = 20
    const val U_AUTOPICK_REMAIN = 21
    const val U_BOOK = 22
    const val U_PHONE_TYPE = 23
    const val U_SEARCH_LIST = 24
    const val U_PAIR_RESULT = 25
    const val U_BTAV_ID3_ALBUM = 26
    const val U_BTAV_GENRE = 27
    const val U_BTAV_PLAY_TIME = 28
    const val U_PBAP_STATE = 29
    const val U_AVRCP14_SUPPORT = 30
    const val U_BT_POWER_ON = 31
    const val U_MIC_LEVEL = 32
    const val U_BT_RING_PERCENT = 33
    const val U_A2DP_SINK_STATE = 34
    const val U_RECORD = 35
    const val U_UPDATE_PROGRESS = 36

    const val U_MIC_MUTED = 42
    const val U_PHONE_VOICE = 43
    const val U_PHONE_BATTERY = 44 // [ints: 1..5]
    const val U_PHONE_SIGNAL = 45 // [ints: 1..5]
    const val U_PHONE_OPERATOR = 46
    const val U_BLUETOOTH_CODING = 47
    const val U_ODB_DEV_CONNECTSTATE = 48
    const val U_PHONE_MESSAGE_HANDLE = 49
    const val U_PHONE_MESSAGE = 50
    const val U_BTAV_ALBUM_IMG = 51
    const val U_BT_RECORD_5LIST = 52
    const val U_BT_NUMBER_NAME = 53
    const val U_CALLING_PIP_ENABLED = 55
    const val U_STOP_DOWNLOAD_BOOK = 56
    const val U_TERMINAL_CMD = 57
    const val U_BLE_STEER_DEV_STATE = 58
    const val U_TRANSFER = 59
    const val U_CALL_NUMBER = 60
    const val U_HFP_STATUS = 61
    const val U_A2DP_STATUS = 62
    const val U_WAITING_NUMBER = 63
    const val U_SUPPORT_MULTI = 64
    const val U_ANSWERING_DEVICE = 65
    const val U_DEVICES_INDEX_SWAP = 66

    const val U_CNT_MAX = 256
}

object BluetoothCommandCodes {
    const val C_BTAV_PREV = 0
    const val C_BTAV_NEXT = 1
    const val C_BTAV_PLAYPAUSE = 2
    const val C_BTAV_PAUSE = 3
    const val C_BTAV_STOP = 4
    const val C_QUERY_PAIR = 5
    const val C_KEY = 6
    const val C_DIAL = 7
    const val C_REDIAL = 8
    const val C_PICKUP = 9
    const val C_HANG = 10
    const val C_REJECT_RING = 11
    const val C_NUMBER = 12
    const val C_LINK_CUT = 13
    const val C_HFP = 14
    const val C_CLEAR = 15
    const val C_HANG_CLEAR_NUM = 16
    const val C_RESET = 17
    const val C_PIN_CODE = 18
    const val C_LOCAL_NAME = 19
    const val C_DISCOVER = 20
    const val C_TEST = 21
    const val C_MIC_VOL = 22
    const val C_MIC_LEVEL = 23
    const val C_CONNECT_DEVICE = 24
    const val C_CONNECT_OBD = 25
    const val C_DOWNLOAD_BOOK = 26
    const val C_BTAV_PLAY = 27  
    const val C_AUTOPICK = 28
    const val C_BT_POWER_ON = 29
    const val C_BTRING_PERCENT = 30
    const val C_BTAV_LINK_CUT = 31
    const val C_BT_HOLD = 32
    const val C_BT_HOLD_HANG = 33
    const val C_BT_CURTALK_HANG = 34
    const val C_BT_DIAL_RECORD = 35
    const val C_BT_RING_RECORD = 36
    const val C_BT_HANG_RECORD = 37
    const val C_BT_DEVICE_UPDATE_FILE = 38
    const val C_CONSOLIDATED_CALLS = 39
    const val C_BT_DEL_RECORD_ONE = 40
    const val C_BT_DEL_RECORD_ALL = 41    
}

  /**
  * No known Bluetooth module get codes
  **/
object BluetoothGetCodes {}

object BluetoothOptions {
            const val BASE = 42
            
            const val MUTE_MIC = 42
            const val PHONE_VOICE = 43
            const val PHONE_BATTERY = 44
            const val PHONE_SIGNAL = 45
            const val PHONE_OPERATOR = 46
            const val BLUETOOTH_CODING = 47
            const val ODB_DEV_CONNECTSTATE = 48
            const val PHONE_MESSAGE_HANDLE = 49
            const val PHONE_MESSAGE = 50
            const val BTAV_ALBUM_IMG = 51
            // C_
            const val BT_RECORD_5LIST = 52
            // C_
            const val BT_NUMBER_NAME = 53
            const val CALLING_PIP_ENABLE = 55
            const val STOP_DOWNLOAD_BOOK = 56
            const val TERMINAL_CMD = 57
            const val BLE_STEER_DEV_STATE = 58
            const val TRANSFER = 59
            const val CALL_NUMBER = 60
            const val HFP_STATUS = 61
            const val A2DP_STATUS = 62
            const val WAITING_NUMBER = 63
            const val SUPPORT_MULTI = 64
            const val ANSWERING_DEVICE = 65
            const val SWAP_DEVICES_INDEX = 66
        }
