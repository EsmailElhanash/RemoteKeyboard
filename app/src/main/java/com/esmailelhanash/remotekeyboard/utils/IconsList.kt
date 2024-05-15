package com.esmailelhanash.remotekeyboard.utils



import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatAlignLeft
import androidx.compose.material.icons.automirrored.filled.FormatAlignRight
import androidx.compose.material.icons.automirrored.filled.FormatIndentDecrease
import androidx.compose.material.icons.automirrored.filled.FormatIndentIncrease
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.automirrored.filled.FormatTextdirectionLToR
import androidx.compose.material.icons.automirrored.filled.FormatTextdirectionRToL
import androidx.compose.material.icons.automirrored.filled.Forward
import androidx.compose.material.icons.automirrored.filled.Grading
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.BrightnessHigh
import androidx.compose.material.icons.filled.BrightnessLow
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.material.icons.filled.FirstPage
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.FlashAuto
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.FlightLand
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Flip
import androidx.compose.material.icons.filled.FlipToBack
import androidx.compose.material.icons.filled.FlipToFront
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.FormatAlignJustify
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatClear
import androidx.compose.material.icons.filled.FormatColorFill
import androidx.compose.material.icons.filled.FormatColorReset
import androidx.compose.material.icons.filled.FormatColorText
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material.icons.filled.FormatLineSpacing
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.FormatShapes
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.FormatStrikethrough
import androidx.compose.material.icons.filled.FormatUnderlined
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Forward30
import androidx.compose.material.icons.filled.Forward5
import androidx.compose.material.icons.filled.FreeBreakfast
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.Functions
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Gesture
import androidx.compose.material.icons.filled.GetApp
import androidx.compose.material.icons.filled.Gif
import androidx.compose.material.icons.filled.GolfCourse
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.GpsNotFixed
import androidx.compose.material.icons.filled.GpsOff
import androidx.compose.material.icons.filled.Grade
import androidx.compose.material.icons.filled.Gradient
import androidx.compose.material.icons.filled.Grain
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.GridOff
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.GroupWork
import androidx.compose.material.icons.filled.Hd
import androidx.compose.material.icons.filled.HdrOff
import androidx.compose.material.icons.filled.HdrOn
import androidx.compose.material.icons.filled.HdrStrong
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LastPage
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Power
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.Work

val iconNameMap = mapOf(
    Icons.Filled.Favorite to "Favorite",
    Icons.Filled.Home to "Home",
    Icons.Filled.Info to "Info",
    Icons.Filled.AccountCircle to "AccountCircle",
    Icons.Filled.Settings to "Settings",
    Icons.Filled.ShoppingCart to "ShoppingCart",
    Icons.Filled.Notifications to "Notifications",
    Icons.Filled.Email to "Email",
    Icons.Filled.AddCircle to "AddCircle",
    Icons.Filled.RemoveCircle to "RemoveCircle",
    Icons.Filled.Delete to "Delete",
    Icons.Filled.Lock to "Lock",
    Icons.Filled.Camera to "Camera",
    Icons.Filled.Palette to "Palette",
    Icons.Filled.Print to "Print",
    Icons.Filled.Warning to "Warning",
    Icons.Filled.Wifi to "Wifi",
    Icons.Filled.Bluetooth to "Bluetooth",
    Icons.Filled.Flight to "Flight",
    Icons.Filled.Work to "Work",
    Icons.Filled.Star to "Star",
    Icons.Filled.PlayArrow to "PlayArrow",
    Icons.Filled.Pause to "Pause",
    Icons.Filled.Stop to "Stop",
    Icons.Filled.SkipNext to "SkipNext",
    Icons.Filled.SkipPrevious to "SkipPrevious",
    Icons.Filled.FirstPage to "FirstPage",
    Icons.Filled.LastPage to "LastPage",
    Icons.Filled.Refresh to "Refresh",
    Icons.Filled.MoreVert to "MoreVert",
    Icons.Filled.MoreHoriz to "MoreHoriz",
    Icons.Filled.BrightnessHigh to "BrightnessHigh",
    Icons.Filled.BrightnessLow to "BrightnessLow",
    Icons.Filled.BrightnessAuto to "BrightnessAuto",
    Icons.Filled.KeyboardArrowUp to "KeyboardArrowUp",
    Icons.Filled.KeyboardArrowDown to "KeyboardArrowDown",
    Icons.AutoMirrored.Filled.KeyboardArrowLeft to "KeyboardArrowLeft",
    Icons.AutoMirrored.Filled.KeyboardArrowRight to "KeyboardArrowRight",
    Icons.Filled.Close to "Close",
    Icons.Filled.Check to "Check",
    Icons.Filled.Clear to "Clear",
    Icons.Filled.Power to "Power",
    Icons.Filled.Sync to "Sync",
    Icons.Filled.Upload to "Upload",
    Icons.Filled.Download to "Download",
    Icons.Filled.Phone to "Phone",
    Icons.Filled.Pin to "Pin",
    Icons.Filled.GpsFixed to "GpsFixed",
    Icons.Filled.GpsNotFixed to "GpsNotFixed",
    Icons.Filled.GpsOff to "GpsOff",
    Icons.Filled.Bookmark to "Bookmark",
    Icons.Filled.Visibility to "Visibility",
    Icons.Filled.VisibilityOff to "VisibilityOff",
    Icons.AutoMirrored.Filled.VolumeUp to "VolumeUp",
    Icons.AutoMirrored.Filled.VolumeOff to "VolumeOff",
    Icons.Filled.Mic to "Mic",
    Icons.Filled.MicOff to "MicOff",
    Icons.Filled.Mail to "Mail",
    Icons.Filled.Edit to "Edit",
    Icons.AutoMirrored.Filled.Send to "Send",
    Icons.Filled.Save to "Save",
    Icons.Filled.Crop to "Crop",
    Icons.Filled.Image to "Image",
    Icons.Filled.Menu to "Menu",
    Icons.Filled.Timer to "Timer",
    Icons.Filled.MusicNote to "MusicNote",
    Icons.Filled.Place to "Place",
    Icons.Filled.Share to "Share",
    Icons.Filled.Event to "Event",
    Icons.Filled.Face to "Face",
    Icons.Filled.FastForward to "FastForward",
    Icons.Filled.FastRewind to "FastRewind",
    Icons.Filled.Fingerprint to "Fingerprint",
    Icons.Filled.Fireplace to "Fireplace",
    Icons.Filled.Flag to "Flag",
    Icons.Filled.FlashAuto to "FlashAuto",
    Icons.Filled.FlashOff to "FlashOff",
    Icons.Filled.FlashOn to "FlashOn",
    Icons.Filled.FlightLand to "FlightLand",
    Icons.Filled.FlightTakeoff to "FlightTakeoff",
    Icons.Filled.Flip to "Flip",
    Icons.Filled.FlipToBack to "FlipToBack",
    Icons.Filled.FlipToFront to "FlipToFront",
    Icons.Filled.Folder to "Folder",
    Icons.Filled.FolderOpen to "FolderOpen",
    Icons.Filled.FormatAlignCenter to "FormatAlignCenter",
    Icons.Filled.FormatAlignJustify to "FormatAlignJustify",
    Icons.AutoMirrored.Filled.FormatAlignLeft to "FormatAlignLeft",
    Icons.AutoMirrored.Filled.FormatAlignRight to "FormatAlignRight",
    Icons.Filled.FormatBold to "FormatBold",
    Icons.Filled.FormatClear to "FormatClear",
    Icons.Filled.FormatColorFill to "FormatColorFill",
    Icons.Filled.FormatColorReset to "FormatColorReset",
    Icons.Filled.FormatColorText to "FormatColorText",
    Icons.AutoMirrored.Filled.FormatIndentDecrease to "FormatIndentDecrease",
    Icons.AutoMirrored.Filled.FormatIndentIncrease to "FormatIndentIncrease",
    Icons.Filled.FormatItalic to "FormatItalic",
    Icons.Filled.FormatLineSpacing to "FormatLineSpacing",
    Icons.AutoMirrored.Filled.FormatListBulleted to "FormatListBulleted",
    Icons.Filled.FormatListNumbered to "FormatListNumbered",
    Icons.Filled.FormatPaint to "FormatPaint",
    Icons.Filled.FormatQuote to "FormatQuote",
    Icons.Filled.FormatShapes to "FormatShapes",
    Icons.Filled.FormatSize to "FormatSize",
    Icons.Filled.FormatStrikethrough to "FormatStrikethrough",
    Icons.AutoMirrored.Filled.FormatTextdirectionLToR to "FormatTextdirectionLToR",
    Icons.AutoMirrored.Filled.FormatTextdirectionRToL to "FormatTextdirectionRToL",
    Icons.Filled.FormatUnderlined to "FormatUnderlined",
    Icons.Filled.Forum to "Forum",
    Icons.AutoMirrored.Filled.Forward to "Forward",
    Icons.Filled.Forward10 to "Forward10",
    Icons.Filled.Forward30 to "Forward30",
    Icons.Filled.Forward5 to "Forward5",
    Icons.Filled.FreeBreakfast to "FreeBreakfast",
    Icons.Filled.Fullscreen to "Fullscreen",
    Icons.Filled.FullscreenExit to "FullscreenExit",
    Icons.Filled.Functions to "Functions",
    Icons.Filled.Gamepad to "Gamepad",
    Icons.Filled.Games to "Games",
    Icons.Filled.Gavel to "Gavel",
    Icons.Filled.Gesture to "Gesture",
    Icons.Filled.GetApp to "GetApp",
    Icons.Filled.Gif to "Gif",
    Icons.Filled.GolfCourse to "GolfCourse",
    Icons.Filled.GpsFixed to "GpsFixed",
    Icons.Filled.Grade to "Grade",
    Icons.Filled.Gradient to "Gradient",
    Icons.AutoMirrored.Filled.Grading to "Grading",
    Icons.Filled.Grain to "Grain",
    Icons.Filled.GraphicEq to "GraphicEq",
    Icons.Filled.GridOff to "GridOff",
    Icons.Filled.GridOn to "GridOn",
    Icons.Filled.Group to "Group",
    Icons.Filled.GroupAdd to "GroupAdd",
    Icons.Filled.GroupWork to "GroupWork",
    Icons.Filled.Hd to "Hd",
    Icons.Filled.HdrOff to "HdrOff",
    Icons.Filled.HdrOn to "HdrOn",
    Icons.Filled.HdrStrong to "HdrStrong",
)

val nameIconMap = iconNameMap.entries.associate { (key, value) -> value to key }

val iconsList = iconNameMap.keys.toList()