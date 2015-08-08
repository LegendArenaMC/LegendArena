/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.utils

import java.util.ArrayList
import java.util.HashMap

public class PagedUtils {

    internal var paged = ArrayList<String>()
    internal var pages = 50
    internal var currentPage = 1
    internal var skipOver = 0

    internal var list = HashMap<Int, ArrayList<String>>()
    internal var lastId = 1

    public constructor() {}

    public constructor(amountInPage: Int) {
        this.pages = amountInPage
    }

    public constructor(amountInPage: Int, page: Int) {
        currentPage = page
        pages = amountInPage
        if(page > 1)
            skipOver = pages * currentPage
    }

    public fun input(input: ArrayList<String>): PagedUtils {
        if(list.isEmpty()) {
            list.put(1, input)
            return this
        }

        list.put(lastId + 1, input)
        lastId += 1

        return this
    }

    public fun output(): ArrayList<String> {
        var lastPageId = 1

        //thanks kotlin[tm]

        if(skipOver < 1) {
            for(a in list.keySet()) {
                for(page in list.get(a)) {
                    if(lastPageId >= pages)
                        break

                    paged.add(page)

                    lastPageId += 1
                }
            }
        } else {
            var skipped = 0
            for(a in list.keySet()) {
                skipped += 1
                if(skipped < skipOver)
                    continue
                for(page in list.get(a)) {
                    if(lastPageId >= pages)
                        break

                    paged.add(page)

                    lastPageId += 1
                }
            }
        }

        return paged
    }

}