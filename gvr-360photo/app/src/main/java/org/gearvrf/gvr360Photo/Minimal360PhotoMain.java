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

import android.widget.ImageView;

import java.util.concurrent.Future;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRRenderData;
import org.gearvrf.GVRScene;
import org.gearvrf.GVRMain;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.scene_objects.GVRSphereSceneObject;
import org.gearvrf.scene_objects.GVRViewSceneObject;
import org.gearvrf.scene_objects.view.GVRFrameLayout;

public class Minimal360PhotoMain extends GVRMain {


    private GVRViewSceneObject mLayoutSceneObject;
    private GVRContext mGVRContext;
    private static final float QUAD_X = 2.0f;
    private static final float QUAD_Y = 1.5f;
    private static final float DEPTH = -1.5f;

    private  final GVRFrameLayout frameLayout;
    private int frameWidth;
    private int frameHeight;

    private int getFrameHeight;
    private  GVRScene mainScene;

    public Minimal360PhotoMain(Minimal360PhotoActivity activity, final GVRFrameLayout frameLayout, final ImageView iv_Screen){
        this.frameLayout = frameLayout;
    }

    @Override
    public void onInit(GVRContext gvrContext) {
        mGVRContext = gvrContext;
        mLayoutSceneObject = new GVRViewSceneObject(mGVRContext, frameLayout, mGVRContext.createQuad(QUAD_X, QUAD_Y));
        // get a handle to the scene
        GVRScene mainScene = gvrContext.getNextMainScene();
/*
        mainScene.getMainCameraRig().getLeftCamera()
                .setBackgroundColor(0.0f, 0.0f, 0.0f, 1.0f);
        mainScene.getMainCameraRig().getRightCamera()
                .setBackgroundColor(0.0f, 0.0f, 0.0f, 1.0f);

        mainScene.getMainCameraRig().getTransform()
                .setPosition(0.0f, 0.0f, 0.0f);

        GVRMesh sphereMesh = mGVRContext.loadMesh(new GVRAndroidResource(
                mGVRContext, R.raw.sphere_mesh));

        GVRSceneObject leftScreen = new GVRSceneObject(mGVRContext, sphereMesh,
                mGVRContext.loadTexture(new GVRAndroidResource(mGVRContext,
                        R.drawable.dom_sphere)));
        leftScreen.getTransform().setScale(10.0f, 10.0f, 10.0f);
        leftScreen.getRenderData().setRenderMask(GVRRenderData.GVRRenderMaskBit.Left);
        GVRSceneObject rightScreen = new GVRSceneObject(mGVRContext,
                sphereMesh, mGVRContext.loadTexture(new GVRAndroidResource(
                mGVRContext, R.drawable.dom_sphere)));
        rightScreen.getTransform().setScale(10.0f, 10.0f, 10.0f);
        rightScreen.getRenderData().setRenderMask(GVRRenderData.GVRRenderMaskBit.Right);

        mainScene.addSceneObject(leftScreen);
        mainScene.addSceneObject(rightScreen);
        */
        GVRSphereSceneObject sphereObject = null;

        // load texture
        Future<GVRTexture> texture = gvrContext.loadFutureTexture(new GVRAndroidResource(gvrContext, R.drawable.dom_sphere));//photosphere));

        // create a sphere scene object with the specified texture and triangles facing inward (the 'false' argument) 
        sphereObject = new GVRSphereSceneObject(gvrContext, false, texture);

        //sphereObject.getRenderData().getMaterial().setOpacity(0.5f);
        // add the scene object to the scene graph
        mainScene.addSceneObject(sphereObject);
        mainScene.getMainCameraRig().getTransform().setPosition(-1.0f, 0.0f, 0.0f);
        mainScene.addSceneObject(mLayoutSceneObject);
        mLayoutSceneObject.getTransform().setPosition(0.0f, 0.0f, -5.0f);
        mLayoutSceneObject.getTransform().rotateByAxisWithPivot(70.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        frameWidth = frameLayout.getWidth();
        frameHeight =  frameLayout.getHeight();


    }

    @Override
    public void onStep() {
    }

}
