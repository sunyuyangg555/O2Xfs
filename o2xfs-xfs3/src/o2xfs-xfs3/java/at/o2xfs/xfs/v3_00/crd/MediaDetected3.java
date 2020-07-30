package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import at.o2xfs.xfs.crd.MediaPosition;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * typedef struct _wfs_crd_media_detected
 * {
 *  WORD wPosition;
 *  USHORT usNumber;
 * } WFSCRDMEDIADETECTED, *LPWFSCRDMEDIADETECTED;
 */
public class MediaDetected3 extends Struct {

    public MediaDetected3(Builder builder) {
        this();
        allocate();
        position.set(builder.mediaPosition);
        number.set(builder.number);
    }

    public static class Builder {
        private MediaPosition mediaPosition = MediaPosition.PRESENT;
        private int number;

        public Builder(MediaPosition mediaPosition, int number) {
            this.mediaPosition = mediaPosition;
            this.number = number;
        }

        public MediaDetected3 build() {
            return new MediaDetected3(this);
        }
    }

    protected final XfsWord<MediaPosition> position = new XfsWord<>(MediaPosition.class);
    protected final USHORT number = new USHORT();

    protected MediaDetected3() {
        add(position);
        add(number);
    }

    public MediaDetected3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public MediaDetected3(MediaDetected3 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(MediaDetected3 copy) {
        position.set(copy.getMediaPosition());
        number.set(copy.number);
    }

    public MediaPosition getMediaPosition() {
        return position.get();
    }

    public int getNumber() {
        return number.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof MediaDetected3)) return false;

        MediaDetected3 that = (MediaDetected3) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(position, that.position)
                .append(getNumber(), that.getNumber())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(position)
                .append(getNumber())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("position", getMediaPosition())
                .append("number", getNumber())
                .toString();
    }
}
