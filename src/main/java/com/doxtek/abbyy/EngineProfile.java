package com.doxtek.abbyy;

// Possible profile names are:
//   "DocumentConversion_Accuracy", "DocumentConversion_Speed",
//   "DocumentArchiving_Accuracy", "DocumentArchiving_Speed",
//   "BookArchiving_Accuracy", "BookArchiving_Speed",
//   "TextExtraction_Accuracy", "TextExtraction_Speed",
//   "FieldLevelRecognition",
//   "BarcodeRecognition_Accuracy", "BarcodeRecognition_Speed",
//   "HighCompressedImageOnlyPdf",
//   "BusinessCardsProcessing",
//   "EngineeringDrawingsProcessing",
//   "Version9Compatibility",
//   "Default"
public enum EngineProfile {
    Default("Default"),
    DocumentConversion_Accuracy("DocumentConversion_Accuracy"),
    DocumentConversion_Speed("DocumentConversion_Speed"),
    DocumentArchiving_Accuracy("DocumentArchiving_Accuracy"),
    DocumentArchiving_Speed("DocumentArchiving_Speed"),
    BookArchiving_Accuracy("BookArchiving_Accuracy"),
    BookArchiving_Speed("BookArchiving_Speed"),
    TextExtraction_Accuracy("TextExtraction_Accuracy"),
    TextExtraction_Speed("TextExtraction_Speed"),
    FieldLevelRecognition("FieldLevelRecognition"),
    BarcodeRecognition_Accuracy("BarcodeRecognition_Accuracy"),
    BarcodeRecognition_Speed("BarcodeRecognition_Speed"),
    HighCompressedImageOnlyPdf("HighCompressedImageOnlyPdf"),
    BusinessCardsProcessing("BusinessCardsProcessing"),
    EngineeringDrawingsProcessing("EngineeringDrawingsProcessing"),
    Version9Compatibility("Version9Compatibility");

    private String mProfile;

    EngineProfile(String profile)
    {
        mProfile = profile;
    }

    @Override
    public String toString() {
        return mProfile;
    }
}
