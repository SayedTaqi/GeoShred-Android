import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AudioRecorder {
    private AudioRecord audioRecord;
    private boolean isRecording;
    private int bufferSize;
    private String filePath;

    public AudioRecorder(String filePath) {
        this.filePath = filePath;
        bufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);
    }

    public void startRecording() {
        audioRecord.startRecording();
        isRecording = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                writeAudioDataToFile();
            }
        }).start();
    }

    private void writeAudioDataToFile() {
        byte[] audioData = new byte[bufferSize];
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(new File(filePath));
            while (isRecording) {
                int read = audioRecord.read(audioData, 0, bufferSize);
                if (read > 0) {
                    os.write(audioData, 0, read);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            audioRecord.stop();
            audioRecord.release();
        }
    }

    public void stopRecording() {
        isRecording = false;
    }
}
