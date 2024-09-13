package com.test.konsist

import com.test.konsist.bad.A as OtherA
import com.test.konsist.good.A

class B {
    fun toto(): A = A(OtherA(0).x == 0)
}
