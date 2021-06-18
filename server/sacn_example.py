import sacn
import time

sender = sacn.sACNsender()
#sender = sacn.sACNsender(bind_address="192.168.1.4")
sender.start()
sender.activate_output(1)
sender.activate_output(2)
sender[1].multicast = True # ??? keep in mind that multicast on windows is a bit different
sender[2].multicast = True

sender.manual_flush = True # turning off the automatic sending of packets
sender[1].dmx_data = (1, 2, 3, 4)  # some test DMX data
sender[2].dmx_data = (5, 6, 7, 8)  # by the time we are here, the above data would be already send out,
# if manual_flush would be False. This could cause some jitter
# so instead we are flushing manual
time.sleep(1) # let the sender initialize itself
sender.flush()
sender.manual_flush = False # keep manual flush off as long as possible, because if it is on, the automatic
# sending of packets is turned off and that is not recommended
sender.stop() # stop sending out