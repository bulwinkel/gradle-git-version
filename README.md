GitVersion
===

GitVersion calculates a unique version name and build number for each commit based on your most recent semantically versioned tag.

Quick Start
---
### Apply plugin
Apply the plugin in the plugins block, latest version can be found [here](https://github.com/bulwinkel/gradle-git-version/releases):
```groovy
plugins {
   id 'com.bulwinkel.gradle.git-version' version '0.2.0'
}
```
Assign the `gitVersion.name` and `gitVersion.buildNumber` to the relevant places in your project, e.g.

Android app or library:
```groovy
android {
  defaultConfig {
    //...
    versionCode gitVersion.buildNumber
    versionName gitVersion.name
  }
  //...
}
```

Java project:
```groovy
group = "com.example"
version = gitVersion.name
```

### Add a tag
```bash
git tag -a 0.0.0 -m "Initial Tag"
```

### Check version information
```bash
./gradlew versionReport
```
This prints a summary of the generated version information to the console, e.g.
```
GitVersion Report
-----------------
gitVersion.name = 0.2.0-2-gc4832210da58
gitVersion.buildNumber = 26
```

License
---
    MIT License
    
    Copyright (c) 2018 Kelvin Bulwinkel
    
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