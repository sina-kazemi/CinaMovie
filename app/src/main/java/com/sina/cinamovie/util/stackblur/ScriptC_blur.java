package com.sina.cinamovie.util.stackblur;

import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.FieldPacker;
import android.renderscript.RSRuntimeException;
import android.renderscript.RenderScript;
import android.renderscript.Script;
import android.renderscript.ScriptC;

public class ScriptC_blur extends ScriptC {
    private static final String __rs_resource_name = "blur";
    // Constructor
    public  ScriptC_blur(RenderScript rs) {
        super(rs,
                __rs_resource_name,
                blurBitCode.getBitCode32(),
                blurBitCode.getBitCode64());
        __ALLOCATION = Element.ALLOCATION(rs);
        __U32 = Element.U32(rs);
    }

    private Element __ALLOCATION;
    private Element __U32;
    private FieldPacker __rs_fp_ALLOCATION;
    private FieldPacker __rs_fp_U32;
    private final static int mExportVarIdx_gIn = 0;
    private Allocation mExportVar_gIn;
    public synchronized void set_gIn(Allocation v) {
        setVar(mExportVarIdx_gIn, v);
        mExportVar_gIn = v;
    }

    public Allocation get_gIn() {
        return mExportVar_gIn;
    }

    public Script.FieldID getFieldID_gIn() {
        return createFieldID(mExportVarIdx_gIn, null);
    }

    private final static int mExportVarIdx_width = 1;
    private long mExportVar_width;
    public synchronized void set_width(long v) {
        if (__rs_fp_U32!= null) {
            __rs_fp_U32.reset();
        } else {
            __rs_fp_U32 = new FieldPacker(4);
        }
        __rs_fp_U32.addU32(v);
        setVar(mExportVarIdx_width, __rs_fp_U32);
        mExportVar_width = v;
    }

    public long get_width() {
        return mExportVar_width;
    }

    public Script.FieldID getFieldID_width() {
        return createFieldID(mExportVarIdx_width, null);
    }

    private final static int mExportVarIdx_height = 2;
    private long mExportVar_height;
    public synchronized void set_height(long v) {
        if (__rs_fp_U32!= null) {
            __rs_fp_U32.reset();
        } else {
            __rs_fp_U32 = new FieldPacker(4);
        }
        __rs_fp_U32.addU32(v);
        setVar(mExportVarIdx_height, __rs_fp_U32);
        mExportVar_height = v;
    }

    public long get_height() {
        return mExportVar_height;
    }

    public Script.FieldID getFieldID_height() {
        return createFieldID(mExportVarIdx_height, null);
    }

    private final static int mExportVarIdx_radius = 3;
    private long mExportVar_radius;
    public synchronized void set_radius(long v) {
        if (__rs_fp_U32!= null) {
            __rs_fp_U32.reset();
        } else {
            __rs_fp_U32 = new FieldPacker(4);
        }
        __rs_fp_U32.addU32(v);
        setVar(mExportVarIdx_radius, __rs_fp_U32);
        mExportVar_radius = v;
    }

    public long get_radius() {
        return mExportVar_radius;
    }

    public Script.FieldID getFieldID_radius() {
        return createFieldID(mExportVarIdx_radius, null);
    }

    //private final static int mExportForEachIdx_root = 0;
    private final static int mExportForEachIdx_blur_v = 1;
    public Script.KernelID getKernelID_blur_v() {
        return createKernelID(mExportForEachIdx_blur_v, 33, null, null);
    }

    public void forEach_blur_v(Allocation ain) {
        forEach_blur_v(ain, null);
    }

    public void forEach_blur_v(Allocation ain, Script.LaunchOptions sc) {
        // check ain
        if (!ain.getType().getElement().isCompatible(__U32)) {
            throw new RSRuntimeException("Type mismatch with U32!");
        }
        forEach(mExportForEachIdx_blur_v, ain, null, null, sc);
    }

    private final static int mExportForEachIdx_blur_h = 2;
    public Script.KernelID getKernelID_blur_h() {
        return createKernelID(mExportForEachIdx_blur_h, 33, null, null);
    }

    public void forEach_blur_h(Allocation ain) {
        forEach_blur_h(ain, null);
    }

    public void forEach_blur_h(Allocation ain, Script.LaunchOptions sc) {
        // check ain
        if (!ain.getType().getElement().isCompatible(__U32)) {
            throw new RSRuntimeException("Type mismatch with U32!");
        }
        forEach(mExportForEachIdx_blur_h, ain, null, null, sc);
    }

}

