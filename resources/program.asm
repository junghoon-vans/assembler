.data
  count 0
  i 4
  kor 8
  eng 12

.code
  loadc #70
  store @kor
  loadc #60
  store @eng

  loada @keyboard
  store @count

  for_loop:
    loada @i
    sub @count
    bz end_for

    loada [sp]
    push #16
    store [sp]
    loadc #15
    store [sp-4]
    jump average

    pop #16

    loada @i
    addc #1
    store @i

    jump for_loop
  end_for:
    halt #0

  sum:
    loada @kor
    adda @eng
    store [sp]
    jump [sp-8]

  average:
    loada [sp]
    push #12
    store [sp-4]
    loadc #31
    store [sp-8]
    jump sum

    loada [sp]
    store [sp-20]
    pop #12

    loada [sp-8]
    div #2
    store [sp-12]
    out [sp-12]
    jump [sp-4]
.end
