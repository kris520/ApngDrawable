# ApngDrawable
ApngDrawable for android, high efficiency, low memory


# Usage
###
		// Init ApngLoader
		ApngLoader.init(this);

		//Load a apng to imageView */
		String apngUri = ApngImageUtils.Scheme.ASSETS.wrap(assetName);
		ApngLoader.loadImage(apngUri, imageView, null);


# Dependencies
[PNGJ v2.1.1](https://github.com/leonbloy/pngj/)

[apng-view](https://github.com/sahasbhop/apng-view)


# License
Copyright 2017.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
