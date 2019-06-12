/*
 * Copyright (c) 2018, Andreas Fagschlunger. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package at.o2xfs.xfs.v3_00.ptr;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.WORD;
import at.o2xfs.xfs.ptr.Alignment;
import at.o2xfs.xfs.ptr.MediaControl;
import at.o2xfs.xfs.ptr.PaperSource;
import at.o2xfs.xfs.ptr.Resolution;
import at.o2xfs.xfs.win32.XfsDWordBitmask;
import at.o2xfs.xfs.win32.XfsMultiByteMap;
import at.o2xfs.xfs.win32.XfsUnicodeMap;
import at.o2xfs.xfs.win32.XfsWord;

public class PrintForm3 extends Struct {


    public static class Builder {

        private final String formName;
        private final String mediaName;
        private Alignment alignment = Alignment.USEFORMDEFN;
        private int offsetX;
        private int offsetY;
        private Resolution resolution = Resolution.LOW;
        private final Set<MediaControl> mediaControl;
        private Map<String, String> fields;
        private Map<String, String> unicodeFields;
        private PaperSource paperSource = PaperSource.ANY;

        public Builder(String formName, String mediaName, Set<MediaControl> mediaControl) {
            this.formName = formName;
            this.mediaName = mediaName;
            this.mediaControl = mediaControl;
        }

        public Builder alignment(Alignment alignment) {
            this.alignment = alignment;
            return this;
        }

        public Builder offsetX(int offsetX) {
            this.offsetX = offsetX;
            return this;
        }

        public Builder offsetY(int offsetY) {
            this.offsetY = offsetY;
            return this;
        }

        public Builder resolution(Resolution resolution) {
            this.resolution = resolution;
            return this;
        }

        public Builder fields(Map<String, String> fields) {
            this.fields = fields;
            return this;
        }

        public Builder unicodeFields(Map<String, String> unicodeFields) {
            this.unicodeFields = unicodeFields;
            return this;
        }

        public Builder paperSource(PaperSource paperSource) {
            this.paperSource = paperSource;
            return this;
        }

        public PrintForm3 build() {
            return new PrintForm3(this);
        }
    }

    public static final int OFFSETUSEFORMDEFN = 0xffff;

    protected final LPSTR formName = new LPSTR();
    protected final LPSTR mediaName = new LPSTR();
    protected final XfsWord<Alignment> alignment = new XfsWord<>(Alignment.class);
    protected final WORD offsetX = new WORD();
    protected final WORD offsetY = new WORD();
    protected final XfsWord<Resolution> resolution = new XfsWord<>(Resolution.class);
    protected final XfsDWordBitmask<MediaControl> mediaControl = new XfsDWordBitmask<>(MediaControl.class);
    protected final XfsMultiByteMap fields = new XfsMultiByteMap();
    protected final XfsUnicodeMap unicodeFields = new XfsUnicodeMap();
    protected final XfsWord<PaperSource> paperSource = new XfsWord<>(PaperSource.class);

    protected PrintForm3() {
        add(formName);
        add(mediaName);
        add(alignment);
        add(offsetX);
        add(offsetY);
        add(resolution);
        add(mediaControl);
        add(fields);
        add(unicodeFields);
        add(paperSource);
    }

    public PrintForm3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public PrintForm3(PrintForm3 copy) {
        this();
        allocate();
        set(copy);
    }

    public PrintForm3(Builder builder) {
        this();
        allocate();
        formName.set(builder.formName);
        mediaName.set(builder.mediaName);
        alignment.set(builder.alignment);
        offsetX.set(builder.offsetX);
        offsetY.set(builder.offsetY);
        resolution.set(builder.resolution);
        mediaControl.set(builder.mediaControl);
        if (builder.fields != null) {
            fields.set(builder.fields);
        }
        if (builder.unicodeFields != null) {
            unicodeFields.set(builder.unicodeFields);
        }
        paperSource.set(builder.paperSource);
    }

    protected void set(PrintForm3 copy) {
        formName.set(copy.getFormName());
        Optional<String> mediaName = copy.getMediaName();
        if (mediaName.isPresent()) {
            this.mediaName.set(mediaName.get());
        }
        alignment.set(copy.getAlignment());
        offsetX.set(copy.getOffsetX());
        offsetY.set(copy.getOffsetY());
        resolution.set(copy.getResolution());
        mediaControl.set(copy.getMediaControl());
        fields.set(copy.getFields());
        unicodeFields.set(copy.getUnicodeFields());
        paperSource.set(copy.getPaperSource());
    }

    public String getFormName() {
        return formName.get();
    }

    public Optional<String> getMediaName() {
        Optional<String> result = Optional.empty();
        if (!Pointer.NULL.equals(mediaName)) {
            result = Optional.of(mediaName.get());
        }
        return result;
    }

    public Alignment getAlignment() {
        return alignment.get();
    }

    public int getOffsetX() {
        return offsetX.get();
    }

    public int getOffsetY() {
        return offsetY.get();
    }

    public Resolution getResolution() {
        return resolution.get();
    }

    public Set<MediaControl> getMediaControl() {
        return mediaControl.get();
    }

    public Map<String, String> getFields() {
        return fields.get();
    }

    public Map<String, String> getUnicodeFields() {
        return unicodeFields.get();
    }

    public PaperSource getPaperSource() {
        return paperSource.get();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getFormName())
                .append(getMediaName())
                .append(getAlignment())
                .append(getOffsetX())
                .append(getOffsetY())
                .append(getResolution())
                .append(getMediaControl())
                .append(getFields())
                .append(getUnicodeFields())
                .append(getPaperSource())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PrintForm3) {
            PrintForm3 printForm3 = (PrintForm3) obj;
            return new EqualsBuilder()
                    .append(getFormName(), printForm3.getFormName())
                    .append(getMediaName(), printForm3.getMediaName())
                    .append(getAlignment(), printForm3.getAlignment())
                    .append(getOffsetX(), printForm3.getOffsetX())
                    .append(getOffsetY(), printForm3.getOffsetY())
                    .append(getResolution(), printForm3.getResolution())
                    .append(getMediaControl(), printForm3.getMediaControl())
                    .append(getFields(), printForm3.getFields())
                    .append(getUnicodeFields(), printForm3.getUnicodeFields())
                    .append(getPaperSource(), printForm3.getPaperSource())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("formName", getFormName())
                .append("mediaName", getMediaName())
                .append("alignment", getAlignment())
                .append("offsetX", getOffsetX())
                .append("offsetY", getOffsetY())
                .append("resolution", getResolution())
                .append("mediaControl", getMediaControl())
                .append("fields", getFields())
                .append("unicodeFields", getUnicodeFields())
                .append("paperSource", getPaperSource())
                .toString();
    }
}
