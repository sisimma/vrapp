/* Copyright 2015 Samsung Electronics Co., LTD
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gearvrf.gvr360Photo;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.github.barteksc.pdfviewer.PDFView;

import org.gearvrf.GVRActivity;
import org.gearvrf.scene_objects.view.GVRFrameLayout;

public class Minimal360PhotoActivity extends GVRActivity
{
    private static final String TAG = "PDFViewActivity";
    private GVRFrameLayout frameLayout;
    private ImageView iv_Screen;
    private final static int REQUEST_CODE = 42;

    public static final String SAMPLE_FILE = "sample.pdf";


    PDFView pdfView;


    Uri uri;


    Integer pageNumber = 0;

    String pdfFileName;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        frameLayout = new GVRFrameLayout(this);
        frameLayout.setBackgroundColor(Color.WHITE);
        View.inflate(this, R.layout.main, frameLayout);
        pdfView = (PDFView) frameLayout.findViewById(R.id.pdfView);

        Minimal360PhotoMain main = new Minimal360PhotoMain(this, frameLayout, iv_Screen);
        setMain(main, "gvr.xml");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId_ = item.getItemId();
        if (itemId_ == R.id.pickFile) {
            pickFile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void pickFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, REQUEST_CODE);
    }
}
