/*
 * The MIT License
 *
 * Copyright 2018 Raymond Buckley.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.ray3k.template;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.SkeletonData;
import com.ray3k.template.AnimationStateDataLoader.*;
import com.ray3k.template.SkeletonDataLoader.*;

public class AnimationStateDataLoader extends AsynchronousAssetLoader<AnimationStateData, AnimationStateDataParameter> {

    AnimationStateData animationStateData;

    public AnimationStateDataLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, AnimationStateDataParameter parameter) {
    }

    @Override
    public AnimationStateData loadSync(AssetManager manager, String fileName, FileHandle file, AnimationStateDataParameter parameter) {
        return animationStateData = new AnimationStateData(manager.get(parameter.skeletonDataFileName));
    }
    
    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, AnimationStateDataParameter parameter) {
        Array<AssetDescriptor> deps = new Array<AssetDescriptor>();
        deps.add(new AssetDescriptor(parameter.skeletonDataFileName, SkeletonData.class, new SkeletonDataLoaderParameter(parameter.atlasName, parameter.scale)));
        return deps;
    }
    
    static public class AnimationStateDataParameter extends AssetLoaderParameters<AnimationStateData> {
        String skeletonDataFileName;
        String atlasName;
        float scale;
        
        public AnimationStateDataParameter(String skeletonDataFileName, String atlasName, float scale) {
            this.skeletonDataFileName = skeletonDataFileName;
            this.atlasName = atlasName;
            this.scale = scale;
        }
    }
}
