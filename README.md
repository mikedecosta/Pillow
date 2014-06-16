Pillow
======

School project similar to Shazam

```
.------------..---------..------------..------------..------------..--------------.
|  ______    ||  _____  ||  _____     ||  _____     ||    ____    || _____  _____ |
| |_   __ \  || |_   _| || |_   _|    || |_   _|    ||  .'    `.  |||_   _||_   _||
|   | |__) | ||   | |   ||   | |      ||   | |      || /  .--.  \ ||  | | /\ | |  |
|   |  ___/  ||   | |   ||   | |   _  ||   | |   _  || | |    | | ||  | |/  \| |  |
|  _| |_     ||  _| |_  ||  _| |__/ | ||  _| |__/ | || \  `--'  / ||  |   /\   |  |
| |_____|    || |_____| || |________| || |________| ||  `.____.'  ||  |__/  \__|  |
|            ||         ||            ||            ||            ||              |
'------------''---------''------------''------------''------------''--------------'
```

 ~~ Info ~~

	Authors: Joeseph Presto, Michael DeCosta, Bryan Pelland
	Contact: presto.j@husky.neu.edu, decosta.m@husky.neu.edu, pelland.b@husky.neu.edu

	Pillow Implementation based off of Shazam in java by Roy van Rijn http://www.redcode.nl/blog/2010/06/creating-shazam-in-java/
    Java runtime environment must be installed to run Pillow

~~ Compile Information ~~

	After downloading the Pillow.tar.gz bundle unpack

		$ tar -xzf Pillow.tgz

	cd to the Pillow dir

		$ cd Pillow

	Finally, run make

		$ make

~~ Running Pillow ~~

	Now you'll be able to run Pillow from the command line
	Pillow can be run using wave, mp3, or text files as inputs
	Text files should only have 1 song file (wave or mp3) per line eg:
		song1.mp3
		song2.mp3

	NOTE: the period '.' is required before /m4500 on the ccis linux machines
	NOTE: Pillow must have Read access on song and text files to run

		$ ./m4500 song1.wav song2.wav

~~ Side effects ~~

	Pillow works off of copies of the two input song files moved into Pillow/working/
	Do not attempt to place anything inside this directory as things are purged from this directory on every call to ./m4500
	Files may be lingering around this directory on an unexpected exit of Pillow


	NOTE: Because of these side effects, m4500 cannot be run concurrently.

~~ End ~~
