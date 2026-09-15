package com.aoe.fytcanbusmonitor

object MainUpdateCodes {

    /**
     * # Power, System & Sleep state
     */
    const val U_APP_ID = 0
    const val U_MCU_ON = 1
    const val U_STANDBY = 2
    const val U_ANY_KEY_BOOT = 5
    const val U_CUT_ACC_POWER = 25
    const val U_AUTO_BLACK_SCREEN = 36
    const val U_APP_VISIBILITY = 39
    const val U_ARM_SLEEP_WAKEUP = 43
    const val U_ACC_ON = 50
    const val U_RESET_ARM_LATER = 61
    const val U_MCU_BOOT_ON = 62
    const val U_CUTACC_TURNOFF_LCDC = 66
    const val U_ECARLINK_ON = 67
    const val U_CUTACC_DELAY_CLOSE_SCREEN = 71
    const val U_SLEEP_AIRPLANE = 76
    const val U_START_STOP_ENABLE = 88
    const val U_SYSTEM_SLEEP_STATE = 103
    const val U_BLACK_STANDBY = 110

    /**
     * # Screen & Display
     */
    const val U_BLACK_SCREEN = 3
    const val U_OSD_TIME = 26
    const val U_BRIGHT_LEVEL = 31
    const val U_BRIGHT_LEVEL_DAY = 32
    const val U_BRIGHT_LEVEL_NIGHT = 33
    const val U_SCREEN_TOUCH_EVENT = 77
    const val U_STATUS_SCREENSHOTS_ENABLE = 117

    /**
     * # Lighting & LEDs
     */
    const val U_LAMPLET = 4
    const val U_LAMPLET_BY_TIME = 29
    const val U_LAMPLET_ON_BOOT = 52
    const val U_LAMPLET_ON_ALWAYS = 53
    const val U_LAMPLET_COLOR_CTRL = 54
    const val U_LAMPLET_CLEAN_ON = 78
    const val U_AMBIENT_LIGHT = 79
    const val U_LED_COLOR = 99

    /**
     * # Navigation & GPS
     */
    const val U_NAVI_ON_BOOT = 6
    const val U_NAVI_PACKAGE = 28
    const val U_GPS_SPEED = 101
    const val U_NAVI_SOUND = 111
    const val U_NAVI_TOP = 113

    /**
     * # Vehicle status & Telemetry
     */
    const val U_HANDBRAKE = 7
    const val U_HANDBRAKE_ENABLE = 8
    const val U_TEMP_OUT = 40
    const val U_STEER_ANGLE = 41
    const val U_TRUNK_CONTROL_STATE = 87
    const val U_CAR_BATTERY_VATAGE = 114
    const val U_STEER_TYPE = 120

    /**
     * # Storage & USB
     */
    const val U_EXIST_SD1_ON_ARM = 9
    const val U_EXIST_SD2_ON_ARM = 10
    const val U_EXIST_USB_ON_ARM = 11
    const val U_USB_ERROR_ENABLE = 100
    const val U_USB_INDEX = 118

    /**
     * # Cameras, Reversing & 360 Panorama
     */
    const val U_BACKCAR = 12
    const val U_BACKCAR_TRACK_ENABLE = 23
    const val U_BACKCAR_MIRROR = 24
    const val U_BACKCAR_TYPE = 47
    const val U_PANORAMA_ON = 55
    const val U_MIRROR_UP_DOWN = 58
    const val U_HOST_BACKCAR_ENABLE = 65
    const val U_REQUEST_CAMERA = 68
    const val U_BACKCAR_360_CAMERA = 81
    const val U_360CAMERA_BACKCAR_ENABLE = 83
    const val U_TRACK_REVERES_ENABLE = 102
    const val U_CAMERA_VOTAGE = 119
    const val U_CAMERA_AHD_ENABLE = 121
    const val U_RESERVING_TPYE = 122

    /**
     * # Parking Radars & Distance Sensors
     */
    const val U_RADAR = 13
    const val U_RADAR_FL = 14
    const val U_RADAR_FML = 15
    const val U_RADAR_FMR = 16
    const val U_RADAR_FR = 17
    const val U_RADAR_RL = 18
    const val U_RADAR_RML = 19
    const val U_RADAR_RMR = 20
    const val U_RADAR_RR = 21
    const val U_BACKCAR_RADAR = 22
    const val U_BACKCAR_RADAR_ENABLE = 27
    const val U_RADAR_POWER = 75
    const val U_RADAR_PARK_ENABLE = 86
    const val U_RADAR_RSF = 90
    const val U_RADAR_RSMF = 91
    const val U_RADAR_RSMB = 92
    const val U_RADAR_RSB = 93
    const val U_RADAR_LSF = 94
    const val U_RADAR_LSMF = 95
    const val U_RADAR_LSMB = 96
    const val U_RADAR_LSB = 97

    /**
     * # Audio, Video, Media & Voice Assistant
     */
    const val U_PLAYER_CMD = 37
    const val U_VA_CMD = 42
    const val U_ID3_TITLE = 49
    const val U_VA_AUDIO_OCCUPIED = 57
    const val U_MCU_REQUEST_VIDEO = 59
    const val U_SIGNAL_ON = 69
    const val U_SIGNAL_NTSC_PAL = 70
    const val U_AUX_ENABLE = 73
    const val U_PLAY_STATUS = 74
    const val U_CNC_AUX_ENABLE = 98
    const val U_VA_AUDIO_OCCUPIED_TO_APP = 104
    const val U_VIDEO_OUTPUT_PARAMETERS = 105
    const val U_MIC_TYPE = 107
    const val U_VIDEO_AUX_TV = 108
    const val U_AUX_IPOD_AUDIO_CHANNEL = 112

    /**
     * # Controls, Keys & Gestures
     */
    const val U_PANEL_KEY_TYPE = 51
    const val U_MCU_DIEECTION_KEY = 60
    const val U_PANEL_KEY_TYPE_CNT = 63
    const val U_GUESTURE = 64
    const val U_MCU_PANEL_KEY_ENABLE = 82
    const val U_ROLL_KEY_TYPE = 89

    /**
     * # Climate / HVAC
     */
    const val U_AIR_CONTROL_SUPPORT_CMD = 72

    /**
     * # MCU Hardware, Diagnostics & Power Rails
     */
    const val U_MCU_VER = 34
    const val U_MCU_SERIAL = 35
    const val U_MCU_POWER_OPTION = 38
    const val U_TIP = 45
    const val U_MCU_ERROR_CODE = 46
    const val U_TIP_MCU_UPGRADE = 48
    const val U_FAN_CYCLE = 56
    const val U_SHOW_FLASH_WRITE_ON = 80
    const val U_SPI_OSD_VER = 84
    const val U_SPI_MCU_VER = 85
    const val U_MCU_MEMORY_CONTROL = 106
    const val U_VCOM_VATAGE = 109
    const val U_CORE_BOARD_CURRENT = 115
    const val U_AVDD = 116

    /**
     * # Reserved
     */
    const val U_RESERVE = 30
    const val U_RESERVE2 = 44

    /**
     * # Max limit
     */
    const val U_CNT_MAX = 256
}

object MainCommandCodes {

    /**
     * # Player commands
     * @renamed from `PLAYER_COMMAND_...`
     */
    const val C_PLAYER_PLAY = 0
    const val C_PLAYER_PLAYPAUSE = 1
    const val C_PLAYER_PAUSE = 2
    const val C_PLAYER_STOP = 3
    const val C_PLAYER_PREV = 4
    const val C_PLAYER_NEXT = 5
    const val C_PLAYER_FF = 6
    const val C_PLAYER_FB = 7
    const val C_PLAYER_NUM = 8
    const val C_PLAYER_REPEAT = 9

   /**
     * # Power, System & Sleep management
     */
    const val C_APP_ID = 0
    const val C_ANY_KEY_BOOT = 2
    const val C_STANDBY = 18
    const val C_RESET_ARM_LATER = 19
    const val C_FACTORY_RESET = 38
    const val C_SYSTEM_PROPERTIES = 41
    const val C_CUTACC_TURNOFF_LCDC = 42
    const val C_ECARLINK_ON = 43
    const val C_ENTER_SLEEP_WAKEUP = 46
    const val C_SLEEP_AIRPLANE = 48
    const val C_ARM_RESET_SELF = 53
    const val C_START_STOP_ENABLE = 61
    const val C_AIRPLANE_MODE = 75
    const val C_EXIT_APP = 81

    /**
     * # Screen & Display settings
     */
    const val C_OSD_TIME = 8
    const val C_BRIGHT_LEVEL = 10
    const val C_BRIGHT_LEVEL_DAY = 11
    const val C_BRIGHT_LEVEL_NIGHT = 12
    const val C_AUTO_BLACK_SCREEN = 13
    const val C_BLACKSCREEN = 16
    const val C_STATUS_SCREENSHOTS_ENABLE = 82
    const val C_ROTATE_SCREEN = 83

    /**
     * # Lighting, Backlight & Ambient LED
     */
    const val C_LAMPLET_BY_TIME = 1
    const val C_LAMPLET_ON_BOOT = 30
    const val C_LAMPLET_ON_ALWAYS = 31
    const val C_LAMPLET_COLOR_CTRL = 32
    const val C_LAMPLET_CLEAN_ON = 49
    const val C_AMBIENT_LIGHT = 50
    const val C_LED_COLOR = 64

    /**
     * # Navigation
     */
    const val C_NAVI_ON_BOOT = 3
    const val C_NAVI_PACKAGE = 9

    /**
     * # Keys, Touch & Controls
     */
    const val C_JUMP_PAGE = 24
    const val C_KEY = 25
    const val C_PANEL_KEY_TYPE = 29
    const val C_GUESTURE = 39
    const val C_MCU_PANEL_KEY_ENABLE = 55
    const val C_TOUCH = 56
    const val C_ROLL_KEY_TYPE = 63
    const val C_STEER_TYPE = 86

    /**
     * # Cameras, Reversing & 360 Panorama
     */
    const val C_BACKCAR_TRACK_ENABLE = 6
    const val C_BACKCAR_MIRROR = 7
    const val C_BACKCAR_TYPE = 28
    const val C_PANORAMA_ON = 33
    const val C_MIRROR_UP_DOWN = 35
    const val C_OUT_BACKCAR = 37
    const val C_HOST_BACKCAR_ENABLE = 40
    const val C_BACKCAR_360_CAMERA = 54
    const val C_360CAMERA_BACKCAR_ENABLE = 57
    const val C_RIGHT_CAMERA_STATE = 60
    const val C_VIDEO_STOP_CAMERA = 66
    const val C_360_CONTROL = 67
    const val C_TRACK_REVERES_ENABLE = 68
    const val C_360_ENTER_BACKCAR = 74
    const val C_360CAMERA_OFF = 76
    const val C_CAMERA_VOTAGE = 85
    const val C_CAMERA_AHD_ENABLE = 87
    const val C_RESERVING_TPYE = 88

    /**
     * # Parking Radars
     */
    const val C_BACKCAR_RADAR_ENABLE = 5
    const val C_RADAR_POWER = 45
    const val C_RADAR_PARK_ENABLE = 59

    /**
     * # Audio, Video & Voice Assistant
     */
    const val C_VA_CMD = 20
    const val C_VIDEO_ID = 22
    const val C_PLAY_INFO = 23
    const val C_VIDEO_IMAGE = 36
    const val C_VIDEO_POSITION = 44
    const val C_AUX_ENABLE = 47
    const val C_VIDEO_OUT_ON = 51
    const val C_VIDEO_OUTPUT_PARAMETERS = 69
    const val C_MIC_TYPE = 71
    const val C_VIDEO_AUX_TV = 72
    const val C_AUDIO_TV_IPOD_CHANNEL = 77
    const val C_BEEP = 80

    /**
     * # MCU, Hardware & Vehicle peripheral control
     */
    const val C_HANDBRAKE_ENABLE = 4
    const val C_MCU_SERIAL = 14
    const val C_MCU_POWER_OPTION = 15
    const val C_MCU_ON = 17
    const val C_MCU_ERROR_CODE = 21
    const val C_LANGUAGE = 26
    const val C_MCU_UPGRADE = 27
    const val C_FAN_CYCLE = 34
    const val C_SHOW_FLASH_WRITE_ON = 52
    const val C_TRUNK_CONTROL_CMD = 58
    const val C_MOTOR_DOWN_UP_BYMCU = 62
    const val C_USB_ERROR_ENABLE = 65
    const val C_MCU_MEMORY_CONTROL = 70
    const val C_VCOM_VOTAGE = 73
    const val C_UPDATA_CONNET_MCU = 78
    const val C_VAVDD = 79
    const val C_GPIO_JNI = 84
}

object MainGetCodes{
    const val G_AUTO_BLACK_SCREEN = 14
    const val G_BACKCAR_RADAR_ON = 28
    const val G_BACKCAR_TRACK_ON = 29
    const val G_BACK_CAR_MUTE_ON = 23
    const val G_BACK_CAR_ON = 26
    const val G_BEEP_ON = 27
    const val G_BLACK_SCREEN_ON = 15
    const val G_BRIGHTNESS_DAY = 18
    const val G_BRIGHTNESS_LEVEL = 35
    const val G_BRIGHTNESS_NIGHT = 19
    const val G_CANBUS_SUPPORT = 41
    const val G_CANBUS_SUPPORT_CNT = 40
    const val G_CANBUS_TYPE = 25
    const val G_CHANNEL = 3
    const val G_DEF_VOL_ON_BOOT = 13
    const val G_DVD_VERSION = 21
    const val G_DVR_ON = 34
    const val G_EXIST_CDC = 7
    const val G_EXIST_DISC = 5
    const val G_EXIST_IPOD = 6
    const val G_FLAGSYNCHRO = 4
    const val G_GPS_LISTEN_ON = 24
    const val G_GPS_MIX_ON = 12
    const val G_GPS_MIX_PERCENT = 38
    const val G_HANDBRAKE_ENABLED = 10
    const val G_HANDBRAKING = 11
    const val G_IPOD_ENABLED = 32
    const val G_LAMPLET_ON = 17
    const val G_LANG = 36
    const val G_MCU_VERSION = 20
    const val G_MUTE_ON = 1
    const val G_NAVI_APP_PACKAGE_NAME = 8
    const val G_OSD_TIME_ON = 16
    const val G_PAGE = 33
    const val G_PLAYER_TYPE = 37
    const val G_POWER_ON = 0
    const val G_POWER_OPTION = 22
    const val G_RADIO_AIR_LINE = 31
    const val G_RESET_DELAY_TIME = 30
    const val G_RUN_NAVI_APP_ON_BOOT = 43
    const val G_STANDBY_ON = 39
    const val G_TV_ENABLED = 44
    const val G_VOL = 2
    const val G_VOL_MAIN_DEF = 42
}
