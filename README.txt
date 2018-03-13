一、应用说明
    一个简单的练习题，在程序里熟练应用各个组件。
二、应用整体设计思路
    1、Model
    程序主要有两三个部分组成，一个程序的显示部分，一个程序的设置部分，最后一个是app Widgets部分（窗口小部件），显示部分主要是一个可以用手指拖动/根据重力感应移动的圆形小球，设置部分主要是对小球的颜色和大小以及操作方式进行参数设置。
    2、View-Activity
    主要有两个Activity，一个是用于显示的MainActivity，还有一个是用于设置的SettingsActivity。 SettingsActivity里主要是一个list控件。
    3、others
    程序第一次开启的时候，小球的坐标是界面上一个（40，40）这个位置，小球颜色为白色，直径为30像素。SettingsActivity中的list的Color选项为White，Size为最小值40。ColorSetting的CheckMark为White。
    MainActivity主要对小球进行拖动操作，程序重启后，小球的位置是最后一次拖动的坐标。
    SettingsActivity的list第一表格引导进入ColorSettingList， 第二表格引导进操作方式的选择list. 第三表格设置小球大小的变化，MainActivity始终保持小球最后一次设置的大小，小球的直径的最大值为100，最小值为0。
    在SettingsActivity中进行的任何设置都要引起mainActivity和设置本身的状态变化。
    比如，颜色修改为蓝色，则mainActivity中的小球颜色变为蓝色，color setting下的提示语则变为蓝色。
	4、Requests
    程序开启的时候始终保持上次最终的状态。
    小球移动范围为界面区。 不允许超出。
    Widgets设置， 用户在打开widget时可以选择指定图标作为widget的图标。 从widget点击进入主界面的时候要及时统计进入次数，并在主界面上面进行显示。 不从widget进入则不显示。 注意， 小球移动区域在两种进入方式下不同。从Widget进入时小球移动区域不包括上层的提示区域.

三、Controller
    MainActivity，SettingsActivity  两个都是简单Activity，SettingsActivity中的list可直接使用preference配置。
Widgets是系统默认的组建。注意及时统计从widgets进入主界面的次数.
	
	
