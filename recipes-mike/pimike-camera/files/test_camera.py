#!/usr/bin/python3
from picamera import PiCamera
with PiCamera() as camera:
    camera.capture('mike.jpg', resize=(1024, 768))

'''
from picamera import PiCamera
import datetime
with PiCamera(resolution='640x480', framerate=24) as camera:
    dateTime = datetime.datetime.now().strftime("%Y-%m-%d_%H-%M-%S")
    camera.start_recording('/home/root/'+dateTime+'.h264', format='h264')
    camera.wait_recording(5)
    camera.stop_recording()

#gst-launch-1.0  -v -e filesrc location=/home/root/2018-03-09_12-47-20.h264  ! h264parse ! rtph264pay ! udpsink  host=192.168.168.200 port=4000


from picamera2 import Picamera2, Preview
import time
with Picamera2() as camera:
    camera_config = camera.create_still_configuration(main={"size": (1920, 1080)}, lores={"size": (640, 480)}, display="lores")
    camera.configure(camera_config)
    camera.start()
    time.sleep(2)
    camera.capture_file("test.jpg")

from picamera2 import Picamera2, Preview
import datetime
with Picamera2(verbose_console=True) as camera:
    dateTime = datetime.datetime.now().strftime("%Y-%m-%d_%H-%M-%S")
    camera_config = camera.create_still_configuration(main={"size": (1920, 1080)}, lores={"size": (640, 480)}, display="lores")
    camera.configure(camera_config)
    camera.start_recording('/home/root/'+dateTime+'.h264', format='h264')
    camera.wait_recording(20)
    camera.stop_recording()
'''