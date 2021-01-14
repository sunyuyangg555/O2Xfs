package at.o2xfs.xfs.v3_10.mcr;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import at.o2xfs.xfs.mcr.McrDeviceState;
import at.o2xfs.xfs.mcr.RetainBin;
import at.o2xfs.xfs.mcr.Shutter;
import at.o2xfs.xfs.mcr.Slots;
import at.o2xfs.xfs.win32.XfsMultiByteMap;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

public class Status310 extends Struct {

    protected final XfsWord<McrDeviceState> deviceState = new XfsWord<>(McrDeviceState.class);
    protected final XfsWord<Slots> slots = new XfsWord<>(Slots.class);
    protected final XfsWord<RetainBin> retainBin = new XfsWord<>(RetainBin.class);
    /**
     * 模块内现有封包个数
     */
    protected final USHORT useSlots = new USHORT();
    /**
     * 取包阀门状态，其可能的取值范围为：
     * 0：阀门故障，1：阀门处于关闭状态，2：阀门处于开启状态；
     */
    protected final XfsWord<Shutter> shutter = new XfsWord<>(Shutter.class);
    /**
     * 自定义信息,以“键=值”字符串形式返回相关的信息。每组字符串以1个空字符NULL标
     * 志结束，最后一组字符串以2个空字符NULL标志结束。要求lpszExtra含有以下字符串：
     * “LastErrorDetail=值”字符串，其中“值”表示最近一次的故障描述，“值”的长度不能超过1024字
     * 节，格式为”ErrorCode | ErrorDetail”。各厂商错误码说明请参看附录部分。
     * “FirmwareVersion=值”字符串，其中“值”表示设备的固件版本信息，“值”的长度不能超过128
     * 字节。
     * “fwShutterBlocked=值”字符串，其中“值”表示阀门口是否有异物阻挡的状态，其可能的取值
     * 范围为：0：传感器故障，1：阀门处有异物遮挡，2：阀门处无异物遮挡；
     * “fwAllSensors=值”字符串，其中“值”表示所有硬件传感器的状态，该字段返回所有硬件传感
     * 器状态，每个传感器状态以一个字节来进行定义，如当阀门传感器状态置为第一个，可能返
     * 回的状态值有0，1，2，那么返回的字段可能是“2XXXXXXXXX”；
     * “MCRRestTime=值”字符串，其中“值”表示最近一次清机完成的时间，格式统一为
     * YYYYMMDDHHMMSS（年月日时分秒）.注意：此时间，整机或模块掉电后，再上电，需要
     * 能再次读取，既需要掉电保持。
     * “fwDoorOpennedSinceMCRRest=值”字符串，其中“值”表示自上一次全盘扫描完成开始到现
     * 在这个时间段内，保险柜安全门是否开启过的标志.1：期间安全门开启过。0：期间安全门并
     * 未开启；此标志每次调用WFS_CMD_MCR_RESET指令成功后，自动清零此标志，期间安
     * 全门有开启，此标志置1.
     * “LastSafeDoorOpenTime=值”字符串，其中“值”表示最近一次保险柜安全门打开时间，格式统
     * 一为YYYYMMDDHHMMSS（年月日时分秒）.注意：此时间，整机或模块掉电后，再上电，
     * 需要能再次读取，既需要掉电保持。
     * “LastSafeDoorCloseTime=值”字符串，其中“值”表示最近一次保险柜安全门打开时间，格式
     * 统一为YYYYMMDDHHMMSS（年月日时分秒）.注意：此时间，整机或模块掉电后，再上
     * 电，需要能再次读取，既需要掉电保持。
     * “fwTroollyStatus=值”字符串，其中“值”表示封包传送小车的介质状态；0表示传送小车为空
     * 车，无物品，1表示传送小车上面有空盘，2表示传送小车上有盘，且盘上有封包或物品。
     * “fwTransPort=值”字符串，其中“值”表示运输机构状态；1传送机构正常，可进行封包传送，2
     * 传送机构异常，不可进行封包传送。
     * “fwHaveScanned=值”字符串，其中“值”表示整机物体清点状态；1 表示整机物品已经完成清
     * 点，0 表示整机物品未完成清点；注意：只要检测到有安全柜门被打开，请将此状态置为未
     * 清点，直至下一次清点完成
     * “fwIsCanDispense=值”字符串，其中“值”表示封包可领取状态；1 模块内有封包，已被清点，
     * 可以正常领取，0 模块不能完成封包领取；注意：造成封包不能正常领取的可能原因有：无
     * 包、未清点、传送机构损坏、扫描部件损坏、阀门损坏等。
     * “fwScannerSt=值”字符串，其中“值”表示用于封包扫描的二维码扫描仪部件状态；1 二维码扫
     * 描仪部件状态正常，可完成二维码扫描，0 二维码扫描仪部件状态异常，异常原因需要体现
     * 在LastErrorDetail中。
     * -“fwStoreBox=值”字符串，其中“值”表示封包箱整体状态或存包机构整体状态,具体来说就是活
     * 动抽屉整体是否全部存放正常或封包箱是否损坏、是否放置到位；1 存包机构整体状态正
     * 常，0 存包机构整体状态异常，异常原因需要体现在LastErrorDetail中。
     * 注意：以上扩展字段的状态请严格按照以上字段定义顺序添加。
     */
    protected final XfsMultiByteMap extra = new XfsMultiByteMap();

    protected Status310() {
        add(deviceState);
        add(slots);
        add(retainBin);
        add(useSlots);
        add(shutter);
        add(extra);
    }

    public Status310(Pointer p) {
        this();
        assignBuffer(p);
    }

    public Status310(Status310 copy) {
        this();
        allocate();
        set(copy);
    }

    protected void set(Status310 copy) {
        deviceState.set(copy.getDeviceState());
        slots.set(copy.getSlots());
        retainBin.set(copy.getRetainBin());
        useSlots.set(copy.getUseSlots());
        shutter.set(copy.getShutter());
        extra.set(copy.getExtra());
    }

    public McrDeviceState getDeviceState() {
        return deviceState.get();
    }

    public Slots getSlots() {
        return slots.get();
    }

    public RetainBin getRetainBin() {
        return retainBin.get();
    }

    public int getUseSlots() {
        return useSlots.get();
    }

    public Shutter getShutter() {
        return shutter.get();
    }

    public Map<String, String> getExtra() {
        return extra.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Status310)) return false;

        Status310 status310 = (Status310) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getDeviceState(), status310.getDeviceState())
                .append(getSlots(), status310.getSlots())
                .append(getRetainBin(), status310.getRetainBin())
                .append(getUseSlots(), status310.getUseSlots())
                .append(getShutter(), status310.getShutter())
                .append(getExtra(), status310.getExtra())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getDeviceState())
                .append(getSlots())
                .append(getRetainBin())
                .append(getUseSlots())
                .append(getShutter())
                .append(getExtra())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("deviceState", getDeviceState())
                .append("slots", getSlots())
                .append("retainBin", getRetainBin())
                .append("useSlots", getUseSlots())
                .append("shutter", getShutter())
                .append("extra", getExtra())
                .toString();
    }
}
