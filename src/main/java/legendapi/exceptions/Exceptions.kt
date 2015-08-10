/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.exceptions

class DoYouEvenKnowWhatYourDoingException : RuntimeException()

class CancelledEventException : RuntimeException()

/**
 * Even I have no idea why this exists.
 */
class InsertNewUserException : RuntimeException("Insert new user and press any key to continue")

class MistakesWereMadeException : RuntimeException("mistakes were very well made.")

/**
 * Don't even ask. Just don't.
 *
 * Spare your sanity. Please.
 */
class PleaseRespectTablesException : RuntimeException()

class YoureOnYourOwnException(s: String) : RuntimeException()

class AreYouDrunkException(s: String) : RuntimeException()