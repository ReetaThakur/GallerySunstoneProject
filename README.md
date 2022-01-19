
# Gallery App Readme file

This is documentation for gallery  app. what I used for building this app those all things are in this file.


## Screenshots
![App Screenshot](https://raw.githubusercontent.com/ReetaThakur/GallerySunstoneProject/master/Screenshot_20220119_142716.png)

![App Screenshot](https://raw.githubusercontent.com/ReetaThakur/GallerySunstoneProject/master/Screenshot_20220119_142725.png)

![App Screenshot](https://raw.githubusercontent.com/ReetaThakur/GallerySunstoneProject/master/Screenshot_20220119_142641.png)

![App Screenshot](https://raw.githubusercontent.com/ReetaThakur/GallerySunstoneProject/master/Screenshot_20220119_142651.png)

![App Screenshot](https://raw.githubusercontent.com/ReetaThakur/GallerySunstoneProject/master/Screenshot_20220119_142702.png)

## Dependencies

    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    //Picasso
    implementation 'com.squareup.picasso:picasso:2.71828'

    //glide
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'

## Description
This is the a gallery app where you can see the all images and videos that present
inside your device(phone). In this app we are reading device external data so
we have to ask user for permission that hey can I have a permission to take
your device images and videos, If user will granted permission then only you
will go ferther otherwise app will close. Once permission is granted then it will
redirect to home screen where you can see two tabs, in one tab you can see all
images and in other tab you can see all videos, and you can swip and switch
between these tabs, If you will click any image then that image will show
in a bigger picture and when you click close button image will close, 
                                                                Just Like
image If you want to play any video you can simply click that video and video
will play with controller and play pause button. For fetching the images and 
videos from your device I used content provider.                                                                

