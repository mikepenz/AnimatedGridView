AnimatedGridView
================

AnimatedGridView extends the default GridView and adds some new functions to animate the deletion of rows.
You just have to choose the AnimatedGridView and extend the AnimatedAdapter (which extends the BaseAdapter).

This library also adds a HeaderGridView, like the normal ListView. The HeaderGridView is from the Google
Source Code of their gallery app.

Including in your project
-------------------------
###Using Maven
AnimatedGridView Library is pushed to [Maven Central], so you just need to add the following dependency to your `build.gradle`.

```javascript
dependencies {
	compile 'com.tundem.widget.gridview:library:1.1.0-SNAPSHOT@aar'
}
```

Usage
-------------------------
### via code or via xml (sample)

```xml
<com.tundem.widget.gridview.AnimatedGridView
    ...
 />
```

### delete fields and animate the gridview
- start the animation by calling following method
```java
animateDeleteCells(mRemovedFieldsToAnimate, 200);
```

- you can also define a listener to do something after the fields were removed
```java
setAnimationListener(new AnimationListener() {
        @Override
        public void onAnimationFinish() {
            ...
	}
});
```

### add fields and animate the gridview
- start the animation by calling following method
```java
//Items to add:
LinkedList<Object> items = new LinkedList<Object>();
items.add(1);
items.add(2);
items.add(3);
items.add(4);
items.add(5);
items.add(6);
//animate the new items
agv.animateAddCells(items, 200);
 ```

Developed By
-------
Mike Penz (@mike_penz)
http://mikepenz.com

License
-------
	Copyright 2014 Mike Penz
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	   http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
       
