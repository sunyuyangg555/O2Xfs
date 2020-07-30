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

package at.o2xfs.xfs.crd;

import at.o2xfs.xfs.XfsConstant;

/**
 * CRD Events:
 * <p>
 * #define WFS_SRVE_CRD_MEDIAREMOVED (CRD_SERVICE_OFFSET + 1)
 * #define WFS_SRVE_CRD_CARDUNITINFOCHANGED (CRD_SERVICE_OFFSET + 2)
 * #define WFS_SRVE_CRD_MEDIADETECTED (CRD_SERVICE_OFFSET + 3)
 * #define WFS_USRE_CRD_CARDUNITTHRESHOLD (CRD_SERVICE_OFFSET + 4)
 * #define WFS_EXEE_CRD_CARDUNITERROR (CRD_SERVICE_OFFSET + 5)
 * #define WFS_SRVE_CRD_DEVICEPOSITION (CRD_SERVICE_OFFSET + 6)
 * #define WFS_SRVE_CRD_POWER_SAVE_CHANGE (CRD_SERVICE_OFFSET + 7)
 */
public enum CrdMessage implements XfsConstant {

    /*
     * @since v3.00
     */
    SRVE_CRD_MEDIAREMOVED(1401),

    SRVE_CRD_CARDUNITINFOCHANGED(1402),
    SRVE_CRD_MEDIADETECTED(1403),
    USRE_CRD_CARDUNITTHRESHOLD(1404),
    EXEE_CRD_CARDUNITERROR(1405),
    SRVE_CRD_DEVICEPOSITION(1406),
    SRVE_CRD_POWER_SAVE_CHANGE(1407);

    private final long value;

    private CrdMessage(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
