package com.test.konsist

import com.test.konsist.iamthealias.A as OtherA
import com.test.konsist.therealone.A

class B {
    fun toto(): A = A(OtherA(0).x == 0)
}
