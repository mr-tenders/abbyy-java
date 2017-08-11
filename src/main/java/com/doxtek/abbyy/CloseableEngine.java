package com.doxtek.abbyy;

import com.abbyy.FREngine.*;

public class CloseableEngine implements AutoCloseable {
    private IEngine mEngine;

    public CloseableEngine(String dllPath, String serialNumber) throws Exception {
        mEngine = Engine.GetEngineObject(dllPath, serialNumber);
    }

    public void LoadPredefinedProfile(EngineProfile profile) {
        LoadPredefinedProfile(profile.toString());
    }

    public void LoadPredefinedProfile(String profile) {
        mEngine.LoadPredefinedProfile(profile);
    }

    public boolean IsPdfWithTextualContent(String imagePath, IImagePasswordCallback cb) {
        return mEngine.IsPdfWithTextualContent(imagePath, cb);
    }

    public IFRDocument CreateFRDocument() {
        return mEngine.CreateFRDocument();
    }

    public CloseableDocument CreateCloseableFRDocument() {
        return new CloseableDocument(CreateFRDocument());
    }

    public IPDFExportParams CreatePDFExportParams() {
        return mEngine.CreatePDFExportParams();
    }

    @Override
    public void close() throws Exception {
        Engine.DeinitializeEngine();
    }

    public IEngine getEngine() {
        return mEngine;
    }
}
