package com.doxtek;

import com.doxtek.abbyy.CloseableDocument;
import com.doxtek.abbyy.CloseableEngine;
import com.doxtek.abbyy.EngineProfile;

import com.abbyy.FREngine.FileExportFormatEnum;
import com.abbyy.FREngine.IPDFExportParams;
import com.abbyy.FREngine.PDFExportScenarioEnum;

import java.io.File;
import java.nio.file.*;

public class Application {
    public static void main(String[] args) throws Exception {
        println("Starting app...");
        new Application().run();
    }

    private static void println(String message) {
        System.out.println(message);
    }

    private void run() throws Exception {
        try(
                CloseableEngine engine = new CloseableEngine(GetDllFolder(), GetDeveloperSN());
        ) {
            processWithEngine(engine);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            println("Finished...");
        }
    }

    private static String GetDllFolder() {
        if( is64BitJVMArchitecture() ) {
            return "C:\\Program Files\\ABBYY SDK\\11\\FineReader Engine\\Bin64";
        } else {
            return "C:\\Program Files\\ABBYY SDK\\11\\FineReader Engine\\Bin";
        }
    }

    private void processWithEngine(CloseableEngine engine) {
        try {
            // Setup FREngine
            setupFREngine(engine);

            // Process sample image
            processImage(engine);
        } catch(Exception ex) {
            println(ex.getMessage());
        }
    }

    private void setupFREngine(CloseableEngine engine) {
        println("Loading predefined profile...");
        engine.LoadPredefinedProfile(EngineProfile.DocumentConversion_Accuracy);
    }

    private void processImage(CloseableEngine engine) {
        String imagePath = GetSamplesFolder() + "\\SampleImages\\Demo.tif";

        try {
            // Don't recognize PDF file with a textual content, just copy it
            if(engine.IsPdfWithTextualContent(imagePath, null)) {
                println( "Copy results..." );
                String resultPath = GetSamplesFolder() + "\\SampleImages\\Demo_copy.pdf";
                Files.copy(Paths.get(imagePath), Paths.get(resultPath), StandardCopyOption.REPLACE_EXISTING);
                return;
            }

            // Create document
            try(
                    CloseableDocument document = engine.CreateCloseableFRDocument();
            ){
                // Add image file to document
                println("Loading image...");
                document.AddImageFile(imagePath, null, null);

                // Process document
                println("Process...");
                document.Process(null);

                // Save results
                println("Saving results...");

                // Save results to rtf with default parameters
                String rtfExportPath = GetSamplesFolder() + "\\SampleImages\\Demo.rtf";
                document.Export(rtfExportPath, FileExportFormatEnum.FEF_RTF, null);

                // Save results to pdf using 'balanced' scenario
                IPDFExportParams pdfParams = engine.CreatePDFExportParams();
                pdfParams.setScenario(PDFExportScenarioEnum.PES_Balanced);

                String pdfExportPath = GetCurrentWorkingDirectory() + "\\Demo.pdf";
                if (!fileExits(pdfExportPath)) {
                    File file = new File(pdfExportPath);
                    if(!file.createNewFile()) {
                        throw new Exception("Cannot create file: " + pdfExportPath);
                    }
                }
                document.Export(pdfExportPath, FileExportFormatEnum.FEF_PDF, pdfParams);
            } catch (Exception ex) {
                throw ex;
            }
        } catch(Exception ex) {
            println(ex.getMessage());
        }
    }

    private boolean fileExits(String path) {
        return (new File(path)).exists();
    }

    private static String GetCurrentWorkingDirectory() {
        return System.getProperty("user.dir");
    }

    // Return developer serial number for FRE
    private static String GetDeveloperSN() {
        return ""; // TODO: Read from file or env
    }

    // Return full path to Samples directory
    private static String GetSamplesFolder() {
        return "C:\\ProgramData\\ABBYY\\SDK\\11\\FineReader Engine\\Samples";
    }

    // Determines whether the JVM architecture is a 64-bit architecture
    private static boolean is64BitJVMArchitecture()
    {
        String jvmKeys [] = {
                "sun.arch.data.model",
                "com.ibm.vm.bitmode",
                "os.arch"
        };
        for( String key : jvmKeys ) {
            String property = System.getProperty( key );
            if( property != null ) {
                if(property.contains("64")) {
                    return true;
                } else if(property.contains("32")) {
                    return false;
                } else if(property.contains("86")) {
                    return false;
                }
            }
        }
        return false;
    }
}

