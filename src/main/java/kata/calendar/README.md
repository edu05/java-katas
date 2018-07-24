# calendar manager kata

The goal of this kata is to practice TDD while using the streams API and optionals.

Meetings are an all time favourite of any office worker. Office workers love meetings so much they sometimes struggle to
find time to schedule more! A decent calendar should help our busy bees spend as much time as possible away from their desk
and inside a meeting room.

1. Allow meetings to be scheduled in a calendar, display them in order (by start time)
2. Do not add a meeting to the calendar if it clashes with another meeting
3. The office worker would like a CalendarOperationResult to reflect the outcome of the scheduling operation. If the meeting
was successfully added to the calendar it should be a successful COR, otherwise the COR should have a failure reason with the details
of any clashing meeting
4. Uh-oh, a colleague has noticed meetings can have an end time before its start time! Prevent this from happening by throwing an IllegalArgumentException
5. The same colleague has noticed another bug (how annoying) - two meetings in the same calendar can have the same name. Avoid this by not adding the to be scheduled meeting
to the calendar and returning a COR with a failure message with the details of the duplicate meeting
6. Try doing 5. using the streams API and without any loops (or the other way round)

