# Driso - Image gallery
### [Watch demo application video](https://drive.google.com/file/d/1ABjD8fHhr_lZOruGlShhdR9mLvVr9ejS/view?usp=sharing)
## Screenshots
Gallery             |  Image View|Zoom in / Out            |  Share Image
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
![](https://github.com/FakhrulASA/driso/blob/master/01.jpg)  |  ![](https://github.com/FakhrulASA/driso/blob/master/02.jpg)| ![](https://github.com/FakhrulASA/driso/blob/master/03.jpg)  |  ![](https://github.com/FakhrulASA/driso/blob/master/04.jpg)
## Feature's implemented :
### 1. Gallery View 
Gallery view implemented using simple recyclerview and gridview
### 2. Infinite scroll
Infinite scroll implemented using simple diffutil, aysnchronous list differ and custom paging
### 3. Image full screen view, zoom in out, tap to zoom
Full screen image view is implemented using PhotoView library for android 
### 4. Save image to gallery
Saving image into gallery is implemented using latest MediaStore API for android, so that it work with with storage permission. For old generation imaged stored in external file directory
### 5. Share image through facebook, gmail and others application
Here is also used latest MediaStore api and then converted to outputstream than sharing it with that Uri
### 6. Caching images
Caching image done simply by Glide DiskCacheStrategy
### 7. Unit test
Unit test implemented for the Gallery API Response checking, is it normally returning body and not returning error.
### 8. App Flavor/Remotely API change
App Flavor/Api scheme changes done with Firebase RemoteConfig where we put a value which can be changed to change application base url. Then the current value is stored in DataStore preference(Local cache)
### 9. API response caching (Not Implemented)
API response caching can be completed using cacheDir() and HttpClient interceptor cache(). But Due to randmized behavior of the api(Randomly changes value on same page on load) I didnt not implemented. 
## Reference
1. PhotoView: https://github.com/Baseflow/PhotoView
2. DataStore: https://developer.android.com/topic/libraries/architecture/datastore?gclsrc=ds&gclsrc=ds
3. Glide: https://github.com/bumptech/glide
