# AndroidAnimation

[![](https://jitpack.io/v/jama5262/AndroidAnimation.svg)](https://jitpack.io/#jama5262/AndroidAnimation)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

An android library for easy and simple view animations

# Table of content
- [Installation](#installation)
- [Usage](#usage)
- [Support](#support)
- [License](#license)

## Installation

Current Version: [![](https://jitpack.io/v/jama5262/AndroidAnimation.svg)](https://jitpack.io/#jama5262/AndroidAnimation)

#### Gradle

Add the following

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

```
dependencies {
    implementation 'com.github.jama5262:AndroidAnimation:0.2.0'
}
```

#### Maven

```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```
<dependency>
    <groupId>com.github.jama5262</groupId>
	<artifactId>AndroidAnimation</artifactId>
    <version>0.2.0</version>
</dependency>
```

Great the project has been setup 👍

## Usage

### Targets

These are views that are to be animated. Simply pass a view or multiple views to animate

There are of two types
- **targetViews()** - takes in views e.g buttons
- **targetChildViews()** - takes in viewgroups e.g linearlayouts. Used to animation child views within the viewgroups

Below is an example to move a view along the x axis

```kotlin
AndroidAnimation().apply {
    targetViews(view1)
    translateX(0f, 700f)
    start()
}

// Or passing a viewgroup to animate its childern

AndroidAnimation().apply {
    targetChildViews(viewGroup1)
    translateX(0f, 700f)
    start()
}
```

You can also pass in multiple view since the target takes in `varargs`

```kotlin
AndroidAnimation().apply {
    targetViews(view1, view2, view3)
    translateX(0f, 700f)
    start()
}

// Or passing multiple viewgroups to animate its childern

AndroidAnimation().apply {
    targetChildViews(viewGroup1, viewGroup2, viewGroup3)
    translateX(0f, 700f)
    start()
}
```

You can also passing in multiple targets for different property animations. Doing this will make all the targeted views to play together.

```kotlin
AndroidAnimation().apply {
    targetViews(view1)
    translateX(0f, 700f)
    translateY(0f, 700f)
    targetViews(view2)
    translateX(0f, 700f)
    rotate(0f, 360f)
    start()
}
```

### ThenPlay

The `thenPlay()` function, allows you to wait for the first target view animation to finish. See example below


```kotlin
AndroidAnimation().apply {
    targetViews(view1)
    translateX(0f, 700f)
    translateY(0f, 700f)
    thenPlay() // Waits for the first targeted views to finish their animation, then plays the next
    targetViews(view2)
    translateX(0f, 700f)
    rotate(0f, 360f)
    start()
}
```

You can also pass in a delay after the first animation has finished in milliseconds

```kotlin
AndroidAnimation().apply {
    targetViews(view1)
    translateX(0f, 700f)
    translateY(0f, 700f)
    thenPlay(2000) // Waits for the first targeted views to finish their animation, then plays the next after 2 sec
    targetViews(view2)
    translateX(0f, 700f)
    rotate(0f, 360f)
    start()
}
```

### Property Animations

This library for now supports the following properties

`x, y, translateX, translateY, rotate, rotateX, rotateY, scaleX, scaleY, alpha`

These properties takes in `vararg values, duration, delay and easing` as parameters. See example below

```kotlin
AndroidAnimation().apply {
    targetViews(view1)
    translateX(0f, 700f, deley = 2000, easing = Easing.LINEAR)
    translateY(0f, 700f, duration = 500)
    start()
}
```

**In terms of priority, the duration parameter overides the [duration](#duration) property**


### Duration, Delay and Easing

#### Duration
 This defines the duration for all targeted views in milliseconds

 ```kotlin
AndroidAnimation().apply {
    duration = 5000 // Animation will take 5 sec to finish
    targetViews(view1, view2)
    translateX(0f, 700f)
    start()
}
```

If you have mutliple views that you want to play at different durations, you can set another duration property to overide the previous one. This also applies to delay and easing. See example below


```kotlin
AndroidAnimation().apply {
    duration = 1000 // Below target view will have a duration of 1 sec
    targetViews(view1)
    translateX(0f, 700f)
    translateY(0f, 700f)
    duration = 3000 // Below target views will have a duration of 3 sec
    targetViews(view2)
    translateX(0f, 700f)
    rotate(0f, 360f)
    start()
}
```

#### Delay
 This defines the delay for all targeted view in milliseconds

```kotlin
AndroidAnimation().apply {
    delay = 3000 // The targeted views will a delay of 3 sec to to start
    targetViews(view1, view2)
    translateX(0f, 700f)
    start()
}
```

Delaying different targets at different times. See example below

```kotlin
AndroidAnimation().apply {
    delay = 1000 // Below target view will have a delay of 1 sec
    targetViews(view1)
    translateX(0f, 700f)
    translateY(0f, 700f)
    delay = 3000 // Below target views will have a delay of 3 sec
    targetViews(view2)
    translateX(0f, 700f)
    rotate(0f, 360f)
    start()
}
```

#### Easing

This defines the easing for all the targeted views

You can find all the available easings from [here](https://github.com/RamiJ3mli/Easings) thanks to [RamiJ3mli](https://github.com/RamiJ3mli)

See example below

```kotlin
AndroidAnimation().apply {
    easing = Easing.ELASTIC_OUT
    targetViews(view1, view2)
    translateX(0f, 700f)
    start()
}
```

### Direction and Looping

#### Direction

This defines the direction of the animation. See example below

```kotlin
AndroidAnimation().apply {
    direction = Direction.REVERSE
    targetViews(view1, view2)
    translateX(0f, 700f)
    start()
}
```

#### Looping

Defines if the animation should loop or not. See example below

```kotlin
AndroidAnimation().apply {
    loop = true
    targetViews(view1, view2)
    translateX(0f, 700f)
    start()
}
```

### Staggering

You can perform staggering by adding the `stagger` parameter to the target functions. See example below

```kotlin
AndroidAnimation().apply {
    targetViews(view1, view2, view3, stagger = 200)
    translateX(0f, 700f)
    translateY(0f, 700f)
    start()
}
```

You can also reverse a stagger animation, by passing the reverse parameter to true. See example below

```kotlin
AndroidAnimation().apply {
    targetViews(view1, view2, view3, stagger = 200, reverse = true)
    translateX(0f, 700f)
    translateY(0f, 700f)
    start()
}
```

### Callbacks

As for now, the callbacks available are the `onAnimationStart` and `onAnimationEnd` callbacks

See example below

```kotlin
AndroidAnimation().apply {
    targetViews(buttonAnimate1)
    targetChildViews()
    translateX(0f, 700f)
    onAnimationStart {
      // Animation has started
    }
    onAnimationEnd {
      // Animation has ended
    }
    start()
}
```

## Support

Reach out to me at one of the following places!

- Email at jama3137@gmail.com
- Twitter [timedjama5262](https://twitter.com/timedjama5262)

## License

```
MIT License

Copyright (c) 2019 Jama Mohamed

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
