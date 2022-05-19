package zizi.polbeng.ac.id.myapplication

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    
    private var mediaPlayer: MediaPlayer? = null
    private var pause = false
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    
    fun startMedia(view: View?) {
        if (pause) {
            mediaPlayer?.seekTo(position)
            mediaPlayer?.start()
            pause = false
        } else {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(applicationContext, R.raw.daughter_is_calling)
                mediaPlayer?.setOnCompletionListener {stopPlayer()}
            }
            mediaPlayer?.start()
        }
    }
    
    fun pauseMedia(view: View?) {
        if (mediaPlayer != null) {
            position = mediaPlayer!!.getCurrentPosition()
            mediaPlayer?.pause()
            pause = true
        }
    }
    
    fun stopMedia(view: View?) {
        stopPlayer()
    }
    
    private fun stopPlayer() {
        if (mediaPlayer != null) {
            pause = false
            position = 0
            mediaPlayer?.release()
            mediaPlayer = null;
            Toast.makeText(this, "Media Player Released", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        stopPlayer()
    }
}