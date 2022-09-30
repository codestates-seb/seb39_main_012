/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable prefer-const */
import {NextApiRequest, NextApiResponse} from 'next'
import formidable from 'formidable'
import fs from 'fs'

export const config = {
  api: {
    bodyParser: false,
  },
}

export default function handler(req: NextApiRequest, res: NextApiResponse) {
  const form = new formidable.IncomingForm({multiples: true})
  form.parse(req, async function (err, fields, files) {
    // console.log('files', files, fields)

    await saveFile(files.file)
    return res.status(201).send('')
  })
}

const saveFile = async (file: any) => {
  for (let fil of file) {
    console.log('Fill', fil.originalFilename)
    const data = fs.readFileSync(fil.filepath)
    fs.writeFileSync(`./public/${fil.originalFilename}`, data)
    await fs.unlinkSync(fil.filepath)
  }

  // const data = fs.readFileSync(file.filepath)
  // console.log('Data', data)
  // fs.writeFileSync(`./public/${file.originalFilename}`, data)
  // await fs.unlinkSync(file.filepath)
  return
}
