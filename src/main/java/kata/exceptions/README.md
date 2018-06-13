# implement a news aggregator for a variety of flaky sources

The goal of this kata is to practice exception handling while practicing TDD.


Carmen is a psychology researcher at UCL. Thanks to UCL she has access to a variety of research repositories like ArXiv
and JSTOR. Every morning she likes to read any recently published papers so she's up to date with the latest news.
To do this, she used to manually go to the ArXiv and JSTOR websites and download all new papers. She's not sure if she's
got a bad internet connection or the ArXiv and JSTOR websites are flaky but she often has to click on the download button
several times for a successful download. She's sure there's a way of automating this menial process, can you help her?

1. Write a `ScientificPaperAggregator` that will aggregate new scientific papers from `ArXivRepository` and
`JSTORRepository` and push them to Carmen's `MobileReaderApp` for her to read in the future.

