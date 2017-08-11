package com.doxtek.abbyy;

import com.abbyy.FREngine.*;

public class CloseableDocument implements AutoCloseable {
    private IFRDocument mDocument;

    public CloseableDocument(IFRDocument document) {
        if (document == null) {
            throw new NullPointerException("IFRDocument is null");
        }
        this.mDocument = document;
    }

    public IFRDocument getDocument() {
        return mDocument;
    }

    public void Process(IDocumentProcessingParams params) {
        mDocument.Process(params);
    }

    public void Export(String exportPath, FileExportFormatEnum format, Object params) {
        mDocument.Export(exportPath, format, params);
    }

    public void AddImageFile(String imagePath, IPrepareImageMode imageMode, IIntsCollection intsCollection) {
        mDocument.AddImageFile(imagePath, imageMode, intsCollection);
    }

    public void Close() {
        mDocument.Close();
    }

    @Override
    public void close() throws Exception {
        mDocument.Close();
    }
}
