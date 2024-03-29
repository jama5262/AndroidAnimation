# AndroidAnimation

[![](https://jitpack.io/v/jama5262/AndroidAnimation.svg)](https://jitpack.io/#jama5262/AndroidAnimation)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

An android library for easy and simple view animations

# Example

Below is an [example](https://github.com/droidconKE/droidconKE2020App/pull/55) of the [droidconKE](https://github.com/droidconKE/droidconKE2020App) app using thie library for splash screen animation

Splash screen animation |
------------ | 
<img src="https://user-images.githubusercontent.com/18502400/77252563-a68d5b80-6c65-11ea-8982-0fbdfe988a93.gif" height="500px"> |

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
    implementation 'com.github.jama5262:AndroidAnimation:<version>'
}
```

Great the project has been setup 👍

## Usage

### Targets

To animate, a view or multiple views, you can use the following target functions

There are of two types
- **targetViews()** - takes in views e.g buttons
- **targetChildViews()** - takes in viewgroups e.g linearlayouts. Used to animation child views within the viewgroups

Below is an example to move a view along the x axis

```kotlin
val animation = androidAnimation {
    targetViews(views = listOf(view1))
    translateX(values = listOf(0f, 700f))
}

animation.start()

// Or passing a viewgroup to animate its children

val animation = androidAnimation {
    targetChildViews(viewGroups = listOf(viewGroup1))
    translateX(values = listOf(0f, 700f))
}

animation.start()
```

You can also pass in multiple views since the target takes in a list

```kotlin
val animation = androidAnimation {
    targetViews(views = listOf(view1, view2, view3))
    translateX(values = listOf(0f, 700f))
}

animation.start()

// Or passing multiple viewgroups to animate its childern

val animation = androidAnimation {
    targetChildViews(viewGroups = listOf(viewGroup1, viewGroup2, viewGroup3))
    translateX(values = listOf(0f, 700f))
}

animation.start()
```

You can also pass in multiple targets for different property animations. Doing this will make all the targeted views to play together.

```kotlin
val animation = androidAnimation {
    targetViews(views = listOf(view1))
    translate(values = listOf(0f, 700f))
    targetViews(views = listOf(view2))
    translateX(values = listOf(0f, 700f))
    rotate(values = listOf(0f, 360f))
}

animation.start()
```

### ThenPlay

The `thenPlay()` function allows you to wait for the first target view animations to finish followed by the second. See example below


```kotlin
val animation = androidAnimation {
    targetViews(views = listOf(view1))
    translate(values = listOf(0f, 700f))
    thenPlay() // Waits for the first targeted views to finish their animation, then plays the next
    targetViews(views = listOf(view2))
    translateX(values = listOf(0f, 700f))
    rotate(values = listOf(0f, 360f))
}

animation.start()
```

You can also pass in a delay after the first animation has finished in milliseconds

```kotlin
val animation = androidAnimation {
    targetViews(views = listOf(view1))
    translate(values = listOf(0f, 700f))
    thenPlay(deley = 2000) // Waits for the first targeted views to finish their animation, then plays the next after 2 sec
    targetViews(views = listOf(view2))
    translateX(values = listOf(0f, 700f))
    rotate(values = listOf(0f, 360f))
}

animation.start()
```

### Property Animations

This library for now supports the following property functions

```kotlin
translate()
translateX()
translateY()
x()
y()
xy()
scaleX()
scaleY()
scale()
rotateX()
rotateY()
rotate()
alpha()
```

These properties takes in `list of float values, duration, delay and easing` as parameters. See example below

```kotlin
val animation = androidAnimation {
    targetViews(views = listOf(view1))
    translateX(listOf(0f, 700f), duration = 500, deley = 2000, easing = Easing.LINEAR)
}

animation.start()
```

**In terms of priority, the duration parameter overrides the [duration](#duration) property**


### Duration, Delay and Easing

#### Duration
This defines the duration for all targeted views in milliseconds

 ```kotlin
val animation = androidAnimation {
    duration = 5000 // Animation will take 5 sec to finish
    targetViews(views = listOf(view1, view2))
    translateX(values = listOf(0f, 700f))
}

animation.start()
```

If you have multiple views that you want to play at different durations, you can set another duration property to override the previous one. 
This also applies to `delay` and `easing`. See example below


```kotlin
val animation = androidAnimation {
    duration = 1000 // Below target view will have a duration of 1 sec
    targetViews(views = listOf(view1))
    translate(values = listOf(0f, 700f))
    duration = 3000 // Below the following target views will have a duration of 3 sec
    targetViews(views = listOf(view2))
    translateX(values = listOf(0f, 700f))
    rotate(values = listOf(0f, 360f))
}

animation.start()
```

#### Delay
 This defines the delay for all targeted view in milliseconds

```kotlin
val animation = androidAnimation {
    delay = 3000 // The targeted views will a delay of 3 sec to to start
    targetViews(views = listOf(view1, view2))
    translateX(values = listOf(0f, 700f))
}

animation.start()
```

Delaying different targets at different times. See example below

```kotlin
val animation = androidAnimation {
    delay = 1000 // Below target view will have a delay of 1 sec
    targetViews(views = listOf(view1))
    translate(values = listOf(0f, 700f))
    delay = 3000 // Below the following target views will have a delay of 3 sec
    targetViews((view2))
    translateX(values = listOf(0f, 700f))
    rotate(values = listOf(0f, 360f))
}

animation.start()
```

#### Easing

This defines the easing for all the targeted views

You can find all the available easings from [here](https://github.com/RamiJ3mli/Easings) thanks to [RamiJ3mli](https://github.com/RamiJ3mli)

See example below

```kotlin
val animation = androidAnimation {
    easing = Easing.ELASTIC_OUT
    targetViews(views = listOf(view1, view2))
    translateX(values = listOf(0f, 700f))
}

animation.start()
```

### Direction and Looping

#### Direction

This defines the direction of the animation. See example below

```kotlin
val animation = androidAnimation {
    targetViews(views = listOf(view1, view2), reverse = true)
    translateX(values = listOf(0f, 700f))
}

animation.start()
```

#### Looping

Defines if the animation should loop or not. See example below

```kotlin
val animation = androidAnimation {
    loop = true
    targetViews(views = listOf(view1, view2))
    translateX(values = listOf(0f, 700f))
}

animation.start()
```

### Staggering

You can perform staggering by adding the `stagger` parameter to the target functions in milliseconds. Meaning each view will animate set amount of milliseconds after the previous one. See example below

```kotlin
val animation = androidAnimation {
    targetViews(views = listOf(view1, view2, view3), stagger = 200)
    translate(values = listOf(0f, 700f))
}

animation.start()
```

You can also reverse a stagger animation, by passing the `reverse` parameter to `true`. See example below

```kotlin
val animation = androidAnimation {
    targetViews(views = listOf(view1, view2, view3), stagger = 200, reverse = true)
    translateX(values = listOf(0f, 700f))
    translateY(values = listOf(0f, 700f))
}

animation.start()
```

### Animation Actions

You can perform different actions to an animations. See below

```kotlin
val animation = androidAnimation {
    // ...
}

animation.start() // Starts the animation
animation.pause() // Pauses the animation
animation.resume() // Resumes the animation
animation.cancel() // Cancel the animation
animation.end() // End the animation

```

### Callbacks

This animation library has the following callbacks available

```kotlin
onAnimationStarted { } // Called when the animation is starts
onAnimationPaused { } // Called when the animation is paused
onAnimationResumed { } // Called when the animation is resumed
onAnimationCanceled { } // Called when the animation is canceled
onAnimationEnded { } // Called when the animation ends
```

Below is how you can use it

```kotlin
val animation = androidAnimation {
    targetViews(views = listOf(view1))
    translateX(values = listOf(0f, 700f))
    
    onAnimationStarted { 
        //Animation is starting
    }
    onAnimationPaused { 
        //Animation is paused
    }
    onAnimationResumed { 
        //Animation is resume
    }
    onAnimationCanceled { 
        //Animation is canceled
    }
    onAnimationEnded { 
        //Animations has ended
    }
}

animation.start()
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
