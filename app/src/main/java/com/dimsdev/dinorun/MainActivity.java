package com.dimsdev.dinorun;

import android.app.*;
import android.os.*;
import com.badlogic.gdx.backends.android.*;

public class MainActivity extends AndroidApplication 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		initialize(new MainEngine(), cfg);
    }
}
