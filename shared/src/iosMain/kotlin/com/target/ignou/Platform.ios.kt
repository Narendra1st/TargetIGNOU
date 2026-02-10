package com.target.ignou

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
}

actual fun getScreenWidthDp(): Int {
    // Placeholder, implement based on iOS screen size
    return 360 // Default phone width
}
