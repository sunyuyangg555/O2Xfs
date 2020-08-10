package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.xfs.crd.Failure;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * typedef struct _wfs_crd_cu_error
 * {
 *  WORD wFailure;
 *  LPWFSCRDCARDUNIT lpCardUnit;
 * } WFSCRDCUERROR, *LPWFSCRDCUERROR;
 *
 */
public class CardUnitError3 extends Struct {

    protected final XfsWord<Failure> failure = new XfsWord<>(Failure.class);
    protected final Pointer cardUnit = new Pointer();

    protected CardUnitError3() {
        add(failure);
        add(cardUnit);
    }

    public CardUnitError3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public CardUnitError3(CardUnitError3 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(CardUnitError3 copy) {
        failure.set(copy.getFailure());
        cardUnit.pointTo(new CardUnit3(copy.getCardUnit()));
    }

    public Failure getFailure() {
        return failure.get();
    }

    public CardUnit3 getCardUnit() {
        return new CardUnit3(cardUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CardUnitError3)) return false;

        CardUnitError3 that = (CardUnitError3) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getFailure(), that.getFailure())
                .append(getCardUnit(), that.getCardUnit())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getFailure())
                .append(getCardUnit())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("failure", getFailure())
                .append("cardUnit", getCardUnit())
                .toString();
    }
}
