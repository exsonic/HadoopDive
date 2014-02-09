import os
import string

with open('1.txt', 'w') as f:
	line = 'I will be CTO. I will be successful. I will be strong!\nYou are a beautiful girl. I miss you so much. I will work hard to match your beauty.\nI will never let you down. You\' be proud of me!'
	for _ in range(100000):
		f.write(line)
