<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('New Item'), ['action' => 'add']) ?></li>
    </ul>
</nav>
<div class="items index large-9 medium-8 columns content">
    <h3><?= __('Items') ?></h3>
    <table cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th><?= $this->Paginator->sort('itemID') ?></th>
                <th><?= $this->Paginator->sort('userID') ?></th>
                <th><?= $this->Paginator->sort('contents') ?></th>
                <th><?= $this->Paginator->sort('createDate') ?></th>
                <th><?= $this->Paginator->sort('updateDate') ?></th>
                <th><?= $this->Paginator->sort('delFlg') ?></th>
                <th class="actions"><?= __('Actions') ?></th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($items as $item): ?>
            <tr>
                <td><?= $this->Number->format($item->itemID) ?></td>
                <td><?= $this->Number->format($item->userID) ?></td>
                <td><?= h($item->contents) ?></td>
                <td><?= h($item->createDate) ?></td>
                <td><?= h($item->updateDate) ?></td>
                <td><?= $this->Number->format($item->delFlg) ?></td>
                <td class="actions">
                    <?= $this->Html->link(__('View'), ['action' => 'view', $item->itemID]) ?>
                    <?= $this->Html->link(__('Edit'), ['action' => 'edit', $item->itemID]) ?>
                    <?= $this->Form->postLink(__('Delete'), ['action' => 'delete', $item->itemID], ['confirm' => __('Are you sure you want to delete # {0}?', $item->itemID)]) ?>
                </td>
            </tr>
            <?php endforeach; ?>
        </tbody>
    </table>
    <div class="paginator">
        <ul class="pagination">
            <?= $this->Paginator->prev('< ' . __('previous')) ?>
            <?= $this->Paginator->numbers() ?>
            <?= $this->Paginator->next(__('next') . ' >') ?>
        </ul>
        <p><?= $this->Paginator->counter() ?></p>
    </div>
</div>
