.data
  count 0
  i 0
  kor 70
  eng 60

.code
  loada @input
  store @count

  for_loop:
    loada @i
    sub @count
    bz @end_for

    // student.average();
    loada [sp]
    push #16
    store [sp]
    loadc #24
    store [sp-4]
    jump @average

    pop #16

    // i = i + 1
    loada @i
    addc #1
    store @i

    jump @for_loop
  end_for:
    halt 0

  sum:
    loada @kor
    adda @eng
    store [sp]
    jump [sp-8]

  average:
    // call sum
    loada [sp]
    push #12
    store [sp-4] // dynamic link
    loadc #51
    store [sp-8] // return address
    jump @sum

    // average의 지역변수 sum에 저장
    loada [sp]
    store [sp-20]
    pop #12

    // average = sum / 2
    loada [sp-8]
    div #2
    store [sp-12]
    out [sp-12]
    jump [sp-4]
.end
