package com.ftdi.j2xx;

import com.ftdi.j2xx.D2xxManager.D2xxException;
import com.ftdi.j2xx.ft4222.FT_4222_Defines;
import com.ftdi.j2xx.ft4222.FT_4222_Defines.SPI_SLAVE_CMD;
import com.ftdi.j2xx.protocol.SpiSlaveResponseEvent;
import com.qualcomm.robotcore.robocol.Command;

/* renamed from: com.ftdi.j2xx.c */
class C0135c extends C0009k {
    C0135c(FT_Device fT_Device) throws D2xxException {
        super(fT_Device);
        m41a((byte) 12);
    }

    short m362a(FT_EEPROM ft_eeprom) {
        int[] iArr = new int[this.b];
        if (ft_eeprom.getClass() != FT_EEPROM_2232H.class) {
            return (short) 1;
        }
        FT_EEPROM_2232H ft_eeprom_2232h = (FT_EEPROM_2232H) ft_eeprom;
        try {
            boolean z;
            if (!ft_eeprom_2232h.A_UART) {
                if (ft_eeprom_2232h.A_FIFO) {
                    iArr[0] = iArr[0] | 1;
                } else if (ft_eeprom_2232h.A_FIFOTarget) {
                    iArr[0] = iArr[0] | 2;
                } else {
                    iArr[0] = iArr[0] | 4;
                }
            }
            if (ft_eeprom_2232h.A_LoadVCP) {
                iArr[0] = iArr[0] | 8;
            }
            if (!ft_eeprom_2232h.B_UART) {
                if (ft_eeprom_2232h.B_FIFO) {
                    iArr[0] = iArr[0] | Command.MAX_COMMAND_LENGTH;
                } else if (ft_eeprom_2232h.B_FIFOTarget) {
                    iArr[0] = iArr[0] | 512;
                } else {
                    iArr[0] = iArr[0] | 1024;
                }
            }
            if (ft_eeprom_2232h.B_LoadVCP) {
                iArr[0] = iArr[0] | 2048;
            }
            if (ft_eeprom_2232h.PowerSaveEnable) {
                iArr[0] = iArr[0] | 32768;
            }
            iArr[1] = ft_eeprom_2232h.VendorId;
            iArr[2] = ft_eeprom_2232h.ProductId;
            iArr[3] = 1792;
            iArr[4] = m42a((Object) ft_eeprom);
            iArr[5] = m55b(ft_eeprom);
            iArr[6] = 0;
            int i = ft_eeprom_2232h.AL_DriveCurrent;
            if (i == -1) {
                i = 0;
            }
            iArr[6] = i | iArr[6];
            if (ft_eeprom_2232h.AL_SlowSlew) {
                iArr[6] = iArr[6] | 4;
            }
            if (ft_eeprom_2232h.AL_SchmittInput) {
                iArr[6] = iArr[6] | 8;
            }
            i = ft_eeprom_2232h.AH_DriveCurrent;
            if (i == -1) {
                i = 0;
            }
            iArr[6] = ((short) (i << 4)) | iArr[6];
            if (ft_eeprom_2232h.AH_SlowSlew) {
                iArr[6] = iArr[6] | 64;
            }
            if (ft_eeprom_2232h.AH_SchmittInput) {
                iArr[6] = iArr[6] | SPI_SLAVE_CMD.SPI_MASTER_TRANSFER;
            }
            i = ft_eeprom_2232h.BL_DriveCurrent;
            if (i == -1) {
                i = 0;
            }
            iArr[6] = ((short) (i << 8)) | iArr[6];
            if (ft_eeprom_2232h.BL_SlowSlew) {
                iArr[6] = iArr[6] | 1024;
            }
            if (ft_eeprom_2232h.BL_SchmittInput) {
                iArr[6] = iArr[6] | 2048;
            }
            iArr[6] = ((short) (ft_eeprom_2232h.BH_DriveCurrent << 12)) | iArr[6];
            if (ft_eeprom_2232h.BH_SlowSlew) {
                iArr[6] = iArr[6] | D2xxManager.FTDI_BREAK_ON;
            }
            if (ft_eeprom_2232h.BH_SchmittInput) {
                iArr[6] = iArr[6] | 32768;
            }
            int i2 = 77;
            if (this.a == (short) 70) {
                i2 = 13;
                z = true;
            } else {
                z = false;
            }
            i2 = m43a(ft_eeprom_2232h.Product, iArr, m43a(ft_eeprom_2232h.Manufacturer, iArr, i2, 7, z), 8, z);
            if (ft_eeprom_2232h.SerNumEnable) {
                m43a(ft_eeprom_2232h.SerialNumber, iArr, i2, 9, z);
            }
            switch (ft_eeprom_2232h.TPRDRV) {
                case SpiSlaveResponseEvent.OK /*0*/:
                    iArr[11] = 0;
                    break;
                case SpiSlaveResponseEvent.DATA_CORRUPTED /*1*/:
                    iArr[11] = 8;
                    break;
                case SpiSlaveResponseEvent.IO_ERROR /*2*/:
                    iArr[11] = 16;
                    break;
                case SpiSlaveResponseEvent.RES_SLAVE_READ /*3*/:
                    iArr[11] = 24;
                    break;
                default:
                    iArr[11] = 0;
                    break;
            }
            iArr[12] = this.a;
            if (iArr[1] == 0 || iArr[2] == 0) {
                return (short) 2;
            }
            if (m52a(iArr, this.b - 1)) {
                return (short) 0;
            }
            return (short) 1;
        } catch (Exception e) {
            e.printStackTrace();
            return (short) 0;
        }
    }

    FT_EEPROM m361a() {
        short s = (short) 0;
        FT_EEPROM ft_eeprom_2232h = new FT_EEPROM_2232H();
        int[] iArr = new int[this.b];
        if (this.c) {
            return ft_eeprom_2232h;
        }
        while (s < this.b) {
            try {
                iArr[s] = m44a(s);
                s = (short) (s + 1);
            } catch (Exception e) {
                return null;
            }
        }
        int i = iArr[0];
        switch ((short) (i & 7)) {
            case SpiSlaveResponseEvent.OK /*0*/:
                ft_eeprom_2232h.A_UART = true;
                break;
            case SpiSlaveResponseEvent.DATA_CORRUPTED /*1*/:
                ft_eeprom_2232h.A_FIFO = true;
                break;
            case SpiSlaveResponseEvent.IO_ERROR /*2*/:
                ft_eeprom_2232h.A_FIFOTarget = true;
                break;
            case FT_4222_Defines.DEBUG_REQ_READ_SFR /*4*/:
                ft_eeprom_2232h.A_FastSerial = true;
                break;
            default:
                ft_eeprom_2232h.A_UART = true;
                break;
        }
        if (((short) ((i & 8) >> 3)) == (short) 1) {
            ft_eeprom_2232h.A_LoadVCP = true;
            ft_eeprom_2232h.A_LoadD2XX = false;
        } else {
            ft_eeprom_2232h.A_LoadVCP = false;
            ft_eeprom_2232h.A_LoadD2XX = true;
        }
        switch ((short) ((i & 1792) >> 8)) {
            case SpiSlaveResponseEvent.OK /*0*/:
                ft_eeprom_2232h.B_UART = true;
                break;
            case SpiSlaveResponseEvent.DATA_CORRUPTED /*1*/:
                ft_eeprom_2232h.B_FIFO = true;
                break;
            case SpiSlaveResponseEvent.IO_ERROR /*2*/:
                ft_eeprom_2232h.B_FIFOTarget = true;
                break;
            case FT_4222_Defines.DEBUG_REQ_READ_SFR /*4*/:
                ft_eeprom_2232h.B_FastSerial = true;
                break;
            default:
                ft_eeprom_2232h.B_UART = true;
                break;
        }
        if (((short) ((i & 2048) >> 11)) == (short) 1) {
            ft_eeprom_2232h.B_LoadVCP = true;
            ft_eeprom_2232h.B_LoadD2XX = false;
        } else {
            ft_eeprom_2232h.B_LoadVCP = false;
            ft_eeprom_2232h.B_LoadD2XX = true;
        }
        if (((short) ((i & 32768) >> 15)) == (short) 1) {
            ft_eeprom_2232h.PowerSaveEnable = true;
        } else {
            ft_eeprom_2232h.PowerSaveEnable = false;
        }
        ft_eeprom_2232h.VendorId = (short) iArr[1];
        ft_eeprom_2232h.ProductId = (short) iArr[2];
        m49a(ft_eeprom_2232h, iArr[4]);
        m50a((Object) ft_eeprom_2232h, iArr[5]);
        switch ((short) (iArr[6] & 3)) {
            case SpiSlaveResponseEvent.OK /*0*/:
                ft_eeprom_2232h.AL_DriveCurrent = (byte) 0;
                break;
            case SpiSlaveResponseEvent.DATA_CORRUPTED /*1*/:
                ft_eeprom_2232h.AL_DriveCurrent = (byte) 1;
                break;
            case SpiSlaveResponseEvent.IO_ERROR /*2*/:
                ft_eeprom_2232h.AL_DriveCurrent = (byte) 2;
                break;
            case SpiSlaveResponseEvent.RES_SLAVE_READ /*3*/:
                ft_eeprom_2232h.AL_DriveCurrent = (byte) 3;
                break;
        }
        if (((short) (iArr[6] & 4)) == (short) 4) {
            ft_eeprom_2232h.AL_SlowSlew = true;
        } else {
            ft_eeprom_2232h.AL_SlowSlew = false;
        }
        if (((short) (iArr[6] & 8)) == (short) 8) {
            ft_eeprom_2232h.AL_SchmittInput = true;
        } else {
            ft_eeprom_2232h.AL_SchmittInput = false;
        }
        switch ((short) ((iArr[6] & 48) >> 4)) {
            case SpiSlaveResponseEvent.OK /*0*/:
                ft_eeprom_2232h.AH_DriveCurrent = (byte) 0;
                break;
            case SpiSlaveResponseEvent.DATA_CORRUPTED /*1*/:
                ft_eeprom_2232h.AH_DriveCurrent = (byte) 1;
                break;
            case SpiSlaveResponseEvent.IO_ERROR /*2*/:
                ft_eeprom_2232h.AH_DriveCurrent = (byte) 2;
                break;
            case SpiSlaveResponseEvent.RES_SLAVE_READ /*3*/:
                ft_eeprom_2232h.AH_DriveCurrent = (byte) 3;
                break;
        }
        if (((short) (iArr[6] & 64)) == (short) 64) {
            ft_eeprom_2232h.AH_SlowSlew = true;
        } else {
            ft_eeprom_2232h.AH_SlowSlew = false;
        }
        s = (short) (iArr[6] & SPI_SLAVE_CMD.SPI_MASTER_TRANSFER);
        if (s == (short) 128) {
            ft_eeprom_2232h.AH_SchmittInput = true;
        } else {
            ft_eeprom_2232h.AH_SchmittInput = false;
        }
        switch ((short) ((iArr[6] & 768) >> 8)) {
            case SpiSlaveResponseEvent.OK /*0*/:
                ft_eeprom_2232h.BL_DriveCurrent = (byte) 0;
                break;
            case SpiSlaveResponseEvent.DATA_CORRUPTED /*1*/:
                ft_eeprom_2232h.BL_DriveCurrent = (byte) 1;
                break;
            case SpiSlaveResponseEvent.IO_ERROR /*2*/:
                ft_eeprom_2232h.BL_DriveCurrent = (byte) 2;
                break;
            case SpiSlaveResponseEvent.RES_SLAVE_READ /*3*/:
                ft_eeprom_2232h.BL_DriveCurrent = (byte) 3;
                break;
        }
        if (((short) (iArr[6] & 1024)) == D2xxManager.FT_FLOW_XON_XOFF) {
            ft_eeprom_2232h.BL_SlowSlew = true;
        } else {
            ft_eeprom_2232h.BL_SlowSlew = false;
        }
        short s2 = (short) (iArr[6] & 2048);
        if (s == (short) 2048) {
            ft_eeprom_2232h.BL_SchmittInput = true;
        } else {
            ft_eeprom_2232h.BL_SchmittInput = false;
        }
        switch ((short) ((iArr[6] & 12288) >> 12)) {
            case SpiSlaveResponseEvent.OK /*0*/:
                ft_eeprom_2232h.BH_DriveCurrent = (byte) 0;
                break;
            case SpiSlaveResponseEvent.DATA_CORRUPTED /*1*/:
                ft_eeprom_2232h.BH_DriveCurrent = (byte) 1;
                break;
            case SpiSlaveResponseEvent.IO_ERROR /*2*/:
                ft_eeprom_2232h.BH_DriveCurrent = (byte) 2;
                break;
            case SpiSlaveResponseEvent.RES_SLAVE_READ /*3*/:
                ft_eeprom_2232h.BH_DriveCurrent = (byte) 3;
                break;
        }
        if (((short) (iArr[6] & D2xxManager.FTDI_BREAK_ON)) == (short) 16384) {
            ft_eeprom_2232h.BH_SlowSlew = true;
        } else {
            ft_eeprom_2232h.BH_SlowSlew = false;
        }
        if (((short) (iArr[6] & 32768)) == Short.MIN_VALUE) {
            ft_eeprom_2232h.BH_SchmittInput = true;
        } else {
            ft_eeprom_2232h.BH_SchmittInput = false;
        }
        s = (short) ((iArr[11] & 24) >> 3);
        if (s < (short) 4) {
            ft_eeprom_2232h.TPRDRV = s;
        } else {
            ft_eeprom_2232h.TPRDRV = 0;
        }
        i = iArr[7] & FT_4222_Defines.CHIPTOP_DEBUG_REQUEST;
        if (this.a == (short) 70) {
            ft_eeprom_2232h.Manufacturer = m47a((i - 128) / 2, iArr);
            ft_eeprom_2232h.Product = m47a(((iArr[8] & FT_4222_Defines.CHIPTOP_DEBUG_REQUEST) - 128) / 2, iArr);
            ft_eeprom_2232h.SerialNumber = m47a(((iArr[9] & FT_4222_Defines.CHIPTOP_DEBUG_REQUEST) - 128) / 2, iArr);
            return ft_eeprom_2232h;
        }
        ft_eeprom_2232h.Manufacturer = m47a(i / 2, iArr);
        ft_eeprom_2232h.Product = m47a((iArr[8] & FT_4222_Defines.CHIPTOP_DEBUG_REQUEST) / 2, iArr);
        ft_eeprom_2232h.SerialNumber = m47a((iArr[9] & FT_4222_Defines.CHIPTOP_DEBUG_REQUEST) / 2, iArr);
        return ft_eeprom_2232h;
    }

    int m364b() {
        int a = m44a((short) 9);
        return (((this.b - 1) - 1) - (((((a & 65280) >> 8) / 2) + ((a & FT_4222_Defines.CHIPTOP_DEBUG_REQUEST) / 2)) + 1)) * 2;
    }

    int m360a(byte[] bArr) {
        if (bArr.length > m364b()) {
            return 0;
        }
        int[] iArr = new int[this.b];
        for (short s = (short) 0; s < this.b; s = (short) (s + 1)) {
            iArr[s] = m44a(s);
        }
        int b = (short) (((this.b - (m364b() / 2)) - 1) - 1);
        int i = 0;
        while (i < bArr.length) {
            int i2;
            if (i + 1 < bArr.length) {
                i2 = bArr[i + 1] & FT_4222_Defines.CHIPTOP_DEBUG_REQUEST;
            } else {
                i2 = 0;
            }
            int i3 = (bArr[i] & FT_4222_Defines.CHIPTOP_DEBUG_REQUEST) | (i2 << 8);
            short s2 = (short) (b + 1);
            iArr[b] = i3;
            i += 2;
            short s3 = s2;
        }
        if (iArr[1] == 0 || iArr[2] == 0 || !m52a(iArr, this.b - 1)) {
            return 0;
        }
        return bArr.length;
    }

    byte[] m363a(int i) {
        byte[] bArr = new byte[i];
        if (i == 0 || i > m364b()) {
            return null;
        }
        short b = (short) (((this.b - (m364b() / 2)) - 1) - 1);
        int i2 = 0;
        while (i2 < i) {
            short s = (short) (b + 1);
            int a = m44a(b);
            if (i2 + 1 < bArr.length) {
                bArr[i2 + 1] = (byte) (a & FT_4222_Defines.CHIPTOP_DEBUG_REQUEST);
            }
            bArr[i2] = (byte) ((a & 65280) >> 8);
            i2 += 2;
            b = s;
        }
        return bArr;
    }
}
