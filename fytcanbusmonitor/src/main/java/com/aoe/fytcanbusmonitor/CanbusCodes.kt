package com.aoe.fytcanbusmonitor

object CanbusCommandCodes {
    
    const val C_CMD_VIDEO = 1;
    const val C_CMD_360_TOUCH_LY = 2;
    const val C_CMD_SPEED_ALARM = 3;
    const val C_CMD_TIME_SETTINGS = 7;
    const val C_CMD_SAFE_DRIVER = 8;
    const val C_CMD_NONSLIP_SET = 9;
    const val C_CMD_STARTPROMPT = 10;
    const val C_CMD_AIR_AUTOClEAN = 11;
    const val C_CMD_INCAR_AUTOVENTILATION = 12;
    const val C_CMD_INCAR_AUTOLOCK = 13;
    const val C_CMD_RAMP_START = 14;

    const val C_CMD_START_REQ_ADD = 100
    const val C_CMD_SET_ADD = 101

    const val C_CANBUS_ID = 1000
    const val C_MISC_BEGIN = 1000

    const val C_DRIVER_ON_RIGHT = 1001
    const val C_SHOW_AIR_WINDOW = 1002
    const val C_CHANGE_PANORAMA = 1003
    const val C_SHOW_DOOR_WINDOW = 1004
    const val C_CAMERA_MODE = 1005
    const val C_CANBUS_FRAME_TO_MCU08 = 1008
    const val C_AIR_WINDOW_ENABLE = 1009
    const val C_DOOR_WINDOW_ENABLE = 1010
    const val C_ONSTAR_SYNC_ON = 1011
    const val C_CANBUS_FRAME_TO_MTU = 1012
    const val C_CANBUS_FRAME_TO_MCU_0X10 = 1013
    const val C_CANBUS_KEY_FUNC = 1014
    const val C_CANBUS_BACKCAR_FUNC = 1015
    const val C_RIGHT_CAMERA_STATE = 1016
    const val C_CANBUS_KEY_FUNC_WC = 1017
    const val C_CANBUS_RC_FUNC_WC = 1018

    const val C_CMD_360_TOUCH = 1029
    const val C_CANBUS_FRAME_TO_MCU30 = 1030
    const val C_PARK_CMD = 1031
    const val C_CANBUSDVR_CMD = 1032
    /**
    *  val i0 = (iArr[0] >> 0) & 1)
    *  val i1 = (iArr[0] >> 1) & 1)
    *  val i2 = (iArr[0] >> 2) & 1)
    *  val i3 = (iArr[0] >> 3) & 1)
    *  val i4 = (iArr[0] >> 4) & 1)
    *  val i5 = (iArr[0] >> 5) & 1)
    *  t0.d.H3(151, 0, i0);
    *  t0.d.H3(151, 1, i1);
    *  t0.d.H3(151, 2, i2);
    *  t0.d.H3(151, 3, i3);
    *  t0.d.H3(151, 4, i4);
    *  t0.d.H3(151, 5, i5);
    * kt.y0(1049, new int[]{6, ((((iArr[0] >> 0) & 1) << 2) & 4) |
    * ((((iArr[0] >> 5) & 1) << 5) & 32) | ((((iArr[0] >> 2) & 1) << 6) & 64) |
    * ((((iArr[0] >> 3) & 1) << 7) & 128) | (16 & (((iArr[0] >> 4) & 1) << 4)) | 
    * ((((iArr[0] >> 1) & 1) << 3) & 8)}, null, null);
    **/
    const val C_CANBUS_CAR_DOOR = 1035
    const val C_CMD_360_TURN_LIGHT_ONOFF = 1036
    const val C_CMD_360_RADAR_ACTIVATION = 1037

    /* OEM / Brand Specific */
    const val C_CANBUS_AUDI_CAR_TYPE = 1033
    const val C_CANBUS_BENZ_BUTTON = 1034
    const val C_CMD_JAHUAR_LZ = 1038
    const val C_CMD_TCROSS_ZH_BUTTON = 1039

    const val C_CMD_6606 = 1040
    // const val C_... = 1041
    const val C_DUDU_OBD = 1042
    const val C_KEYCODE_STUDY = 1043 // [0/1]
    


}

object CanbusUpdateCodes {

    const val U_CANBUS_ID = 1000
    const val U_CANBUS_FRAME_TO_UI = 1019


    /**
     * # Basic
     * It should be supported by every canbox
     */
    const val U_DOOR_BEGIN = 0
    const val U_DOOR_ENGINE = 0
    const val U_DOOR_FL = 1
    const val U_DOOR_FR = 2
    const val U_DOOR_RL = 3
    const val U_DOOR_RR = 4
    const val U_DOOR_BACK = 5
    const val U_DOOR_END = 6

    /**
    * # "Proprietary" or not?
    */
    const val U_CARINFO_TIME = 49;
    const val U_CARINFO_LANE_DEVIATION_SYS_STATE = 64;
    const val U_CARINFO_CRASH_SWITCH = 65;
    const val U_CARINFO_360_WORK_STATE = 67;
    const val U_CARINFO_ACC_WORK_MODE = 69;
    const val U_CARINFO_AUTO_BRAKE_ASSIST_SWITCH = 78;
    const val U_CARINFO_SUPERFASTCHARGE_STATE = 87;
    const val U_CARINFO_SUPERFASTCHARGE_NUMBER = 88;

    /**
    # "Proprietary" codes
    * Can vary depends on exact selected canbox manuf./model/profile/fw ver.
     * Usually used by `com.syu.canbus` where every "group" of cars have their own code logic
    * Range: (maybe) 94 - (maybe) 499
    **/

    // 'Fiat `All` models' (whatever that means) from Hiworld canbus box
    // (I renamed "Fieyate" to "Fiat" - the Chinese don't pay attention to typos and "WC" to "HIWORLD" conv.)
    const val U_HIWORLD_FIAT_ALL_CARINFO_BEGIN = 98
    const val U_HIWORLD_FIAT_ALL_CARINFO_TRIPCURR_AVG_FUEL_COMP = 99
    const val U_HIWORLD_FIAT_ALL_CARINFO_TRIPCURR_REMAIN_FUEL_DIST = 100
    const val U_HIWORLD_FIAT_ALL_CARINFO_TRIPCURR_TOTAL_MILAGE = 101
    const val U_HIWORLD_FIAT_ALL_CARINFO_TRIPA_AVG_FUEL_COMP = 102
    const val U_HIWORLD_FIAT_ALL_CARINFO_TRIPA_AVG_SPEED = 103
    const val U_HIWORLD_FIAT_ALL_CARINFO_TRIPA_TRAVEL_MILAGE = 104
    const val U_HIWORLD_FIAT_ALL_CARINFO_TRIPA_TRAVEL_TIME = 105
    const val U_HIWORLD_FIAT_ALL_CARINFO_TRIPB_AVG_FUEL_COMP = 106
    const val U_HIWORLD_FIAT_ALL_CARINFO_TRIPB_AVG_SPEED = 107
    const val U_HIWORLD_FIAT_ALL_CARINFO_TRIPB_TRAVEL_MILAGE = 108
    const val U_HIWORLD_FIAT_ALL_CARINFO_TRIPB_TRAVEL_TIME = 109
    const val U_HIWORLD_FIAT_ALL_CARINFO_END = 110
    /* # End Section */

    // Some other propably car specific codes 
    /*const val U_CARINFO_NONSLIP_STATE = 98;
    const val U_CARINFO_STARTPROMPT = 99;
    const val U_CAR_BACKCAR = 101;
    const val U_CARINFO_NONSLIP_STATE_ENABLE = 102;
    const val U_CARINFO_INCAR_AUTOVENTILATION_ONOFF = 104;
    const val U_CARINFO_INCAR_AUTOVENTILATION_ENABLE = 105;
    const val U_CARINFO_BCM_ENABLE = 106;
    const val U_CARINFO_INCAR_AUTOLOCK_ONOFF = 107;
    const val U_CARINFO_NONSLIP_STATE_NEW = 111;
    const val U_CARINFO_AIR_AUTOClEAN_ONOFF = 112;
    const val U_CARINFO_RAMP_START = 114;
    */

    /**
     * # "Universal" codes
     * My guess is that they are "universal" codes and that exact codes are read by "universal" apps e.g. by DUDU UI.
     */
    const val U_CAR_ADD_START = 500
    const val U_CAR_FRAME_NUM = 501
    
    /* Exterior Lighting & Indicators */
    const val U_CAR_LIGHT = 517
    const val U_CAR_LIGHT_WIDTH = 502
    const val U_CAR_LIGHT_NEAR = 503
    const val U_CAR_LIGHT_FAR = 504
    const val U_CAR_LIGHT_LEFT = 505
    const val U_CAR_LIGHT_RIGHT = 506
    const val U_CAR_LIGHT_FRONT = 507
    const val U_CAR_LIGHT_REAR = 508
    const val U_CAR_LIGHT_ENABLE = 541
    const val U_CAR_LIGHT_WIDTH_ENABLE = 526
    const val U_CAR_LIGHT_NEAR_ENABLE = 527
    const val U_CAR_LIGHT_FAR_ENABLE = 528
    const val U_CAR_LIGHT_LEFT_ENABLE = 529
    const val U_CAR_LIGHT_RIGHT_ENABLE = 530
    const val U_CAR_LIGHT_FRONT_ENABLE = 531
    const val U_CAR_LIGHT_REAR_ENABLE = 532
    
    const val U_CAR_WIPER_LEV = 509
    const val U_CAR_CUR_SPEED = 510
    const val U_CAR_AVG_SPEED = 511
    const val U_CAR_TOTAL_MILEAGE = 512
    const val U_CAR_DRIVENABLE_MILEAGE = 513
    const val U_CAR_SEAT_BELT_LEFT = 514
    const val U_CAR_SEAT_BELT_RIGHT = 515
    const val U_CAR_ACCON = 516
    const val U_CAR_REAR_BACK = 518
    const val U_CAR_HANDBRAKE = 519
    const val U_CAR_CUR_FUEL = 520
    const val U_CAR_AVG_FUEL = 521
    const val U_CAR_TEMP_WATER = 522
    const val U_CAR_TEMP_MOTOR_OIL = 523
    const val U_CAR_ENGINE_SPEED = 524
    const val U_CAR_VOLTAGE = 525
    const val U_CAR_WIPER_LEV_ENABLE = 533
    const val U_CAR_CUR_SPEED_ENABLE = 534
    const val U_CAR_AVG_SPEED_ENABLE = 535
    const val U_CAR_TOTAL_MILEAGE_ENABLE = 536
    const val U_CAR_DRIVENABLE_MILEAGE_ENABLE = 537
    const val U_CAR_SEAT_BELT_LEFT_ENABLE = 538
    const val U_CAR_SEAT_BELT_RIGHT_ENABLE = 539
    const val U_CAR_ACCON_ENABLE = 540
    const val U_CAR_REAR_BACK_ENABLE = 542
    const val U_CAR_HANDBRAKE_ENABLE = 543
    const val U_CAR_CUR_FUEL_ENABLE = 544
    const val U_CAR_AVG_FUEL_ENABLE = 545
    const val U_CAR_TEMP_WATER_ENABLE = 546
    const val U_CAR_TEMP_MOTOR_OIL_ENABLE = 547
    const val U_CAR_ENGINE_SPEED_ENABLE = 548
    const val U_CAR_VOLTAGE_ENABLE = 549
    const val U_CAR_DOOR_ENABLE = 550
    const val U_CAR_STEER_ANGLE_ENABLE = 551
    const val U_CAR_TEMP_OUT_ENABLE = 552
    const val U_DOOR_ENGINE_ADD = 553
    const val U_DOOR_FL_ADD = 554
    const val U_DOOR_FR_ADD = 555
    const val U_DOOR_RL_ADD = 556
    const val U_DOOR_RR_ADD = 557
    const val U_DOOR_BACK_ADD = 558
    const val U_SPEED_UNIT = 559
    const val U_CAR_ADD_END = 560

    /* Probably a reserve for future codes (561-...) */

    const val U_AIR_WINDOW_ENABLE = 1001
    const val U_DOOR_WINDOW_ENABLE = 1002
    const val U_DRIVER_ON_RIGHT = 1003
    const val U_EXIST_DOOR = 1004
    const val U_CANBUS_VER = 1005
    const val U_TIP_ID = 1006
    const val U_EXIST_AIR = 1007
    const val U_SHOW_AIR_WINDOW = 1008
    const val U_MCU_CANBUS_SUPPORT_CNT = 1010
    const val U_SHOW_DOOR_WINDOW = 1011
    const val U_EXIST_TEMP_OUT = 1012
    const val U_AIR_CONTROL_PAGE = 1014
    const val U_EXIST_AIR_CONTROL = 1018
    const val U_CANBUS_AIR_VER = 1023
    const val U_CANBUS_FRAME_TO_MTU = 1024
    const val U_CANBUS_SLAVECAR_TOUCH_CALI = 1025
    const val U_CANBUS_SLAVECAR_BACKLIGHT = 1026
    const val U_CANBUS_PM25_CAR_IN = 1027
    const val U_CANBUS_PM25_CAR_OUT = 1028
    const val U_CANBUS_PM25_ENABLE = 1029
    const val U_CUR_SPEED = 1031
    const val U_ENGINE_SPEED = 1032
    const val U_GPS_ANGLE = 1033
    const val U_EXIST_CAR_ROTATING = 1035
    const val U_UISERVR_TYPE = 1037
    const val U_NEW_AIREIXT_SP = 1041
    const val U_CANBUS_CAR_DOOR = 1043
    const val U_CANBUS_CAR_AIR = 1044
    const val U_6606_CARINF = 1049
    const val U_EXIST_CARTIRE = 1050

    /**
     * # Cameras, Parking & Radars
     */
    const val U_EXIST_PANORAMA = 1009
    const val U_CAMERA_MODE = 1013
    const val U_RIGHT_CAMERA_ON_OFF = 1017
    const val U_RIGHT_CAMERA_STATE = 1020
    const val U_ORI_CARBACK = 1021
    const val U_SHOW_BACKCAR_HOST = 1030
    const val U_EXIST_CAR_DVR = 1034
    const val U_PARK_MODE = 1039
    const val U_REAR_MOVE_TYPE = 1040
    const val U_EXIST_SIDERADAR = 1046

    /**
     * # Audio, Radio, Bluetooth & OnStar
     */
    const val U_CAR_BT_ON = 1015
    const val U_EXIST_CAR_RADIO = 1016
    const val U_ONSTAR_SYNC_ON = 1022
    const val U_HIDE_RADIO = 1036
    const val U_BT_RECORD_5LIST = 1045

    /**
     * # OEM / Brand Specific
     */
    const val U_BMW_CARUI_ONOFF = 1038
    const val U_CANBUS_BENZ_BUTTON = 1042
    const val U_JAHUAR_LZ_RADAR = 1047
    const val U_TCROSS_ZH_BUTTON = 1048

    /**
     * # Air condition status
     */
    const val U_AIR_BEGIN = 10
    const val U_AIR_POWER = 10
    const val U_AIR_AC = 11
    const val U_AIR_CYCLE = 12
    const val U_AIR_AUTO = 13
    const val U_AIR_DUAL = 14
    const val U_AIR_MAX_FRONT = 15
    const val U_AIR_REAR_DEFROST = 16
    const val U_AIR_FRONT_HOT = 17
    const val U_AIR_BLOW_UP_LEFT = 18
    const val U_AIR_BLOW_BODY_LEFT = 19
    const val U_AIR_BLOW_FOOT_LEFT = 20
    const val U_AIR_WIND_LEVEL_LEFT = 21
    const val U_AIR_BLOW_UP_RIGHT = 22
    const val U_AIR_BLOW_BODY_RIGHT = 23
    const val U_AIR_BLOW_FOOT_RIGHT = 24
    const val U_AIR_WIND_LEVEL_RIGHT = 25
    const val U_AIR_AUTO_RIGHT = 26
    const val U_AIR_TEMP_LEFT = 27
    const val U_AIR_TEMP_RIGHT = 28
    const val U_AIR_SEAT_HOT_LEFT = 29
    const val U_AIR_SEAT_HOT_RIGHT = 30
    const val U_AIR_SEAT_BLOW_LEFT = 31
    const val U_AIR_SEAT_BLOW_RIGHT = 32
    const val U_AIR_FLOW_AUTO = 33
    const val U_AIR_NANOE = 34
    const val U_AIR_SWING = 35
    const val U_AIR_CYCLE_AUTO = 36
    const val U_AIR_TEMP_UNIT = 37
    const val U_AIR_REAR = 38
    const val U_AIR_REAR_DUAL = 39
    const val U_AIR_REAR_TEMP_LEFT = 40
    const val U_AIR_REAR_TEMP_RIGHT = 41
    const val U_AIR_REAR_POWER = 42
    const val U_AIR_REAR_AUTO = 43
    const val U_AIR_REAR_WIN_LEV = 44
    const val U_AIR_REAR_VIEW_HOT = 45
    const val U_AIR_REAR_BLOW_BODY = 46
    const val U_AIR_REAR_BLOW_FOOT = 47
    const val U_AIR_REAR_BLOW_UP = 48
    const val U_AIR_BLOW_AUTO_LEFT = 49
    const val U_AIR_BLOW_AUTO_RIGHT = 50
    const val U_AIR_WIND_STRENGTH = 51
    const val U_AIR_ECO = 52
    const val U_AIR_ACMAX = 53
    const val U_AIR_AQS = 54
    const val U_AIR_MONO = 55
    const val U_AIR_TRMP_SET_V = 56
    const val U_AIR_ZONE = 57
    const val U_AIR_ION = 58
    const val U_AIR_REARVIEW_HOT = 59
    const val U_AIR_FULL_LEFT = 60
    const val U_AIR_FULL_RIGHT = 61
    const val U_AIR_SYNC = 62
    const val U_AIR_HEAT = 63
    const val U_CARINFO_AIR_LEV = 64
    const val U_AIR_FRONT_DEFROST = 65
    const val U_AIR_HOT_STEER = 66
    const val U_AIR_REAR_LOCK = 67
    const val U_AIR_PTC = 68
    const val U_AUTO_LEV = 69
    const val U_AIR_FAST = 70
    const val U_AIR_SOFT = 71
    const val U_AIR_BLOW_HEAD = 72
    const val U_AIR_AUTO_WIN_LEV = 73
    const val U_AIR_AUTO_WIN_BLOW = 74
    const val U_AIR_TEMP_TYPE = 75
    const val U_AIR_CLEAN = 76
    const val U_AIR_BLOW_MODE_LEFT = 77
    const val U_AIR_REAR_AC = 78
    const val U_AIR_FULL = 79
    const val U_AIR_PARK = 80
    const val U_AIR_REAR_AUTO_RIGHT = 81
    const val U_AIR_REAR_BLOW_BODY_RIGHT = 82
    const val U_AIR_REAR_BLOW_FOOT_RIGHT = 83
    const val U_AIR_REAR_BLOW_UP_RIGHT = 84
    const val U_AIR_REAR_COOL = 85
    const val U_AIR_REAR_MANUAL = 86
    const val U_AIR_BLOW_MODE_RIGHT = 87
    const val U_AIR_REAR_SEAT_HOT_LEFT = 88
    const val U_AIR_REAR_SEAT_HOT_RIGHT = 89
    const val U_AIR_REAR_SEAT_BLOW_LEFT = 90
    const val U_AIR_REAR_SEAT_BLOW_RIGHT = 91
    const val U_AIR_FRONT_ONLY = 92
    const val U_AIR_END = 93

    /**
     * # Max limit
     */
    const val U_CNT_MAX = 1200
}

object CanbusKeycodes {
    const val KEYCODE_DEFAULT = -1

    const val KEYCODE_POWER = 0
    const val KEYCODE_NAVI = 1
    const val KEYCODE_MODE = 2
    const val KEYCODE_PREV = 3
    const val KEYCODE_NEXT = 4
    const val KEYCODE_HOME = 5
    const val KEYCODE_BACK = 6
    const val KEYCODE_VOL_UP = 7
    const val KEYCODE_VOL_DOWN = 8
    const val KEYCODE_MENU = 9
    const val KEYCODE_ALL_APPS = 10
    const val KEYCODE_EJECT = 11
    const val KEYCODE_MUTE = 12
    const val KEYCODE_VA = 13
    const val KEYCODE_DIM = 14
    const val KEYCODE_RECENT_TASK = 15
    const val KEYCODE_PLAYPAUSE = 16
    const val KEYCODE_CAMERA = 17
    const val KEYCODE_PHONE = 18
    const val KEYCODE_TONE_PLUS = 19
    const val KEYCODE_TONE_MINUS = 20
    const val KEYCODE_BAND = 21
    const val KEYCODE_DVD = 22
    const val KEYCODE_PANORAMA = 23
    const val KEYCODE_RADIO = 24
    const val KEYCODE_SCREEN_OFF = 25
    const val KEYCODE_HANG = 26
    const val KEYCODE_FF = 27
    const val KEYCODE_FB = 28
    const val KEYCODE_ENTER = 29
    const val KEYCODE_ROLL_LEFT = 30
    const val KEYCODE_ROLL_RIGHT = 31
    const val KEYCODE_LEFT = 32
    const val KEYCODE_RIGHT = 33
    const val KEYCODE_UP = 34
    const val KEYCODE_DOWN = 35
    const val KEYCODE_PLAY = 36
    const val KEYCODE_PAUSE = 37
    const val KEYCODE_EQ = 38
    const val KEYCODE_SEARCH = 39
    const val KEYCODE_NUM1 = 40
    const val KEYCODE_NUM2 = 41
    const val KEYCODE_NUM3 = 42
    const val KEYCODE_NUM4 = 43
    const val KEYCODE_NUM5 = 44
    const val KEYCODE_NUM6 = 45
    const val KEYCODE_NUM7 = 46
    const val KEYCODE_NUM8 = 47
    const val KEYCODE_NUM9 = 48
    const val KEYCODE_NUM0 = 49
    const val KEYCODE_NX = 50
    const val KEYCODE_NJ = 51
    const val KEYCODE_TV = 52
    const val KEYCODE_AUX = 53
    const val KEYCODE_MEDIAPLAYER = 54
    const val KEYCODE_CARSETTTINGS = 55
    const val KEYCODE_TIMESETTING = 56
    const val KEYCODE_CALI = 57
    const val KEYCODE_SYSTEMINFO = 58
    const val KEYCODE_DVR = 59
    const val KEYCODE_CARUSB = 60
    const val KEYCODE_CARRADIO = 61
    const val KEYCODE_ACCONTROL = 62
    const val KEYCODE_CANBUS = 63
    const val KEYCODE_SCAN = 64
    const val KEYCODE_REPEAT = 65
    const val KEYCODE_RANDOM = 66
    const val KEYCODE_SEEK_UP = 67
    const val KEYCODE_SEEK_DOWN = 68
    const val KEYCODE_STAND_BY = 69
    const val KEYCODE_DISPLAY = 70
    const val KEYCODE_BRIGHT_DEC = 71
    const val KEYCODE_BRIGHT_INC = 72
    const val KEYCODE_AUDIOSETTINGS = 73
    const val KEYCODE_FM = 74
    const val KEYCODE_AM = 75
    const val KEYCODE_BT_AV = 76
    const val KEYCODE_NC = 77
    const val KEYCODE_TA = 78
}
