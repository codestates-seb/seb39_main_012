import 'react-day-picker/dist/style.css'
import React, {useState} from 'react'
import {format} from 'date-fns'
import {DateRange, DayPicker, SelectRangeEventHandler} from 'react-day-picker'
import styled from 'styled-components'
import {colors} from '@/styles/colors'

interface Props {
  setValue: React.Dispatch<
    React.SetStateAction<{
      local: string
      checkin: string
      checkout: string
      dogSize: string
    }>
  >
}

function DayPickerRange({setValue}: Props) {
  const [selectedRange, setSelectedRange] = useState<DateRange>()

  const handleRangeSelect: SelectRangeEventHandler = (range: DateRange | undefined) => {
    setSelectedRange(range)
    if (range?.from) {
      setValue((pre) => ({...pre, checkin: format(range?.from as Date, 'yyyy-MM-dd')}))
    }
    if (range?.to) setValue((pre) => ({...pre, checkout: format(range?.to as Date, 'yyyy-MM-dd')}))
  }

  return (
    <>
      <PickerBox>
        <DayPicker
          mode="range"
          selected={selectedRange}
          onSelect={handleRangeSelect}
          numberOfMonths={1}
          pagedNavigation
        />
      </PickerBox>
    </>
  )
}

export default DayPickerRange

const PickerBox = styled.div`
  position: absolute;
  background-color: white;

  .rdp-day_selected:not([disabled]),
  .rdp-day_selected:focus:not([disabled]),
  .rdp-day_selected:active:not([disabled]),
  .rdp-day_selected:hover:not([disabled]) {
    color: white;
    background-color: ${colors.mainColor};
    border: none;
  }

  .rdp {
    --rdp-cell-size: 40px;
    --rdp-accent-color: ${colors.mainColor2};
    --rdp-background-color: #e7edff;
    --rdp-accent-color-dark: #3003e1;
    --rdp-background-color-dark: #180270;
    --rdp-outline: 2px solid var(--rdp-accent-color);
    --rdp-outline-selected: solid 1px yellow;
    margin: 1em;
  }

  @media (max-width: 768px) {
    font-size: 10px;
  }
`
