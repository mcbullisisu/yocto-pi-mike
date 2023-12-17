#!/usr/bin/python3

import logging
import logging.handlers
import time
from RPi import GPIO
from picamera import PiCamera


# BCM mode allows us to use the labels on the Adafruit T-Cobbler Plus.
# Board mode makes you use pin numbers
# Example BCM GPIO 18 = Board Pin 12
GPIO.setmode(GPIO.BCM)
GPIO.setup(18, GPIO.IN)

camera = PiCamera()

# Set up logging for /var/log/messages
logger = logging.getLogger('MyLogger')
logger.setLevel(logging.DEBUG)
handler = logging.handlers.SysLogHandler(address = '/dev/log')
formatter = logging.Formatter('[PirMotionCamera] %(message)s')
handler.setFormatter(formatter)
logger.addHandler(handler)

def handle_video_preview_for_pir(channel):
    pir_detected_motion = GPIO.input(18)
    message_to_log = 'Motion started!' if pir_detected_motion else 'Motion stopped...'
    logger.info(message_to_log)

    if pir_detected_motion:
        camera.start_preview()
    else:
        camera.stop_preview()


GPIO.add_event_detect(18, GPIO.BOTH, callback=handle_video_preview_for_pir, bouncetime=100)
while(True):
    time.sleep(0.25)
