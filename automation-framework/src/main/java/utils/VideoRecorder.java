package utils;



import org.monte.media.*;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecorder extends ScreenRecorder {

    public VideoRecorder(GraphicsConfiguration cfg,
                         Rectangle captureArea,
                         Format fileFormat,
                         Format screenFormat,
                         Format mouseFormat,
                         Format audioFormat) throws Exception {

        super(cfg,
              captureArea,
              fileFormat,
              screenFormat,
              mouseFormat,
              audioFormat);
    }

    public static VideoRecorder startRecording(String testName)
            throws Exception {


        File folder = new File("./videos");

        if (!folder.exists()) {
            folder.mkdirs();
        }


        GraphicsConfiguration gc =
                GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();


        Rectangle screen =
                new Rectangle(
                Toolkit.getDefaultToolkit()
                .getScreenSize()
                );


        Format fileFormat =
                new Format(
                MediaTypeKey,
                MediaType.FILE,
                MimeTypeKey,
                MIME_AVI
                );


        Format screenFormat =
                new Format(
                MediaTypeKey,
                MediaType.VIDEO,
                EncodingKey,
                ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                CompressorNameKey,
                ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                DepthKey,
                24,
                FrameRateKey,
                Rational.valueOf(15),
                QualityKey,
                1.0f,
                KeyFrameIntervalKey,
                15 * 60
                );


        VideoRecorder recorder =
                new VideoRecorder(
                gc,
                screen,
                fileFormat,
                screenFormat,
                null,
                null
                );


        recorder.start();


        System.out.println(
        "Recording started: " + testName
        );

         System.out.println("Recoder : "+recorder);
        return recorder;
    }
    }


