# mp3 player kata

The goal of this kata is to play a little bit with the List and Map APIs while practicing TDD and being introduced to the Streams API

1. Songs can be searched by their full title after they've been added to the MP3

2. Songs can be searched by their partial title

3. Songs can be searched by their partial artist

4. Refactor the code to use the Streams API

5. MP3s normally allow you see some statistics, like how many songs per artist you have

6. Can we do it without the null check?

7. Some MP3s can connect to the internet to provide extra information about the songs in them

8. Some times MP3s can't connect to the internet because there's no signal, represent this with the InternetProvider component
of your MP3 throwing a NoSignalException and in such cases still return the minimum song info (title and artist)